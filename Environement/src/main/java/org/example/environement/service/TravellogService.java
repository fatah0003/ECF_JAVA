package org.example.environement.service;

import org.example.environement.dto.travellogs.TravellogDtoReceive;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoStat;
import org.example.environement.entity.Observation;
import org.example.environement.entity.Travellog;
import org.example.environement.exception.NotFoundException;
import org.example.environement.repository.ObservationRepository;
import org.example.environement.repository.TravellogRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravellogService {

    private final TravellogRepository travellogRepository;
    private final ObservationRepository observationRepository;

    public TravellogService(TravellogRepository travellogRepository, ObservationRepository observationRepository) {
        this.travellogRepository = travellogRepository;
        this.observationRepository = observationRepository;
    }

    public TravellogDtoResponse create(Long observationId, TravellogDtoReceive dtoReceive) {
        Observation observation = observationRepository.findById(observationId)
                .orElseThrow(() -> new NotFoundException("Observation not found"));

        Travellog travellog = dtoReceive.dtoToEntity();
        travellog.setObservation(observation);

        Travellog saved = travellogRepository.save(travellog);

        return entityToDto(saved);
    }

    public List<TravellogDtoResponse> getAll(int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return travellogRepository.findAll(PageRequest.of(pageNumber, pageSize).withSort(sort))
                .getContent()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public TravellogDtoStat getStatsByObservation(Long observationId) {
        List<Travellog> logs = travellogRepository.findByObservationId(observationId);

        TravellogDtoStat stats = new TravellogDtoStat();

        for (Travellog log : logs) {
            stats.addTotalDistanceKm(log.getDistanceKm());
            double co2 = log.calculateCO2();
            stats.addTotalEmissionsKg(co2);

            String modeStr = log.getMode().name();
            stats.getByMode().merge(modeStr, co2, Double::sum);
        }

        return stats;
    }

    private TravellogDtoResponse entityToDto(Travellog travellog) {
        return TravellogDtoResponse.builder()
                .id(travellog.getId())
                .distanceKm(travellog.getDistanceKm())
                .mode(travellog.getMode().name())
                .estimatedCo2Kg(travellog.calculateCO2())
                .build();
    }


    public List<TravellogDtoResponse> get(int pageSize) {
        return getAll(0, pageSize);
    }

    public TravellogDtoStat getStat(Long observationId) {
        return getStatsByObservation(observationId);
    }
}
