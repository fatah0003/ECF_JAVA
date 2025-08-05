package org.example.environement.dto.observation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.environement.dto.travellogs.TravellogDtoReceive;
import org.example.environement.entity.Observation;
import org.example.environement.exception.NotFoundException;
import org.example.environement.repository.SpecieRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ObservationDtoReceive {
    private String observerName;
    private String location;
    private Double latitude,longitude;
    private String observationDateStr;
    private String comment;
    private long specieId;
    private List<TravellogDtoReceive> travellogs;

    public Observation dtoToEntity (SpecieRepository specieRepository){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Observation observationCreated = Observation.builder()
                .observerName(this.getObserverName())
                .location(this.getLocation())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .observationDate(LocalDate.parse(this.getObservationDateStr(),format))
                .comment(this.getComment())
                .specie(specieRepository.findById(this.getSpecieId()).orElseThrow(NotFoundException::new))
                .travellogs(this.getTravellogs().stream().map(TravellogDtoReceive::dtoToEntity).collect(Collectors.toList()))
                .build();

        observationCreated.getTravellogs().forEach(t -> t.setObservation(observationCreated));
        return observationCreated;
    }
}
