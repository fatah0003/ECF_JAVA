package org.example.environement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.entity.enums.TravelMode;

@Entity
@Table(name = "travellog")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Travellog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double distanceKm;

    @Enumerated(EnumType.STRING)
    private TravelMode mode;

    @ManyToOne
    @JoinColumn(name = "observation_id")
    private Observation observation;

    public double calculateCO2() {
        switch (mode) {
            case BIKE -> {
                return 0;
            }
            case CAR -> {
                return distanceKm * 0.22;
            }
            case BUS -> {
                return distanceKm * 0.11;
            }
            case TRAIN -> {
                return distanceKm * 0.03;
            }
            case PLANE -> {
                return distanceKm * 0.259;
            }
            default -> {
                return 0;
            }
        }
    }

    public TravellogDtoResponse entityToDto() {
        return TravellogDtoResponse.builder()
                .id(this.id)
                .distanceKm(this.distanceKm)
                .mode(this.mode.toString())
                .estimatedCo2Kg(this.calculateCO2())
                .build();
    }


}
