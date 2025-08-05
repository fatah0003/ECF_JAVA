package org.example.environement.dto.travellogs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TravellogDtoResponse {
    private long id;
    private double distanceKm;
    private String mode;
    private double estimatedCo2Kg;
}
