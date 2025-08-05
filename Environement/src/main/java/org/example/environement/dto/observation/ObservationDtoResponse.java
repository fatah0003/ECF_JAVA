package org.example.environement.dto.observation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.environement.dto.specie.SpecieDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoResponse;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ObservationDtoResponse {
    private long id;
    private String observerName;
    private String location;
    private Double latitude,longitude;
    private LocalDate observationDate;
    private String comment;
    private SpecieDtoResponse specie;
    private List<TravellogDtoResponse> travellogs;

}
