package org.example.environement.dto.travellogs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.environement.entity.Travellog;
import org.example.environement.entity.enums.TravelMode;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TravellogDtoReceive {
    private double distanceKm;
    private String mode;

    public Travellog dtoToEntity (){
        Travellog travellog = Travellog.builder()
                .distanceKm(this.getDistanceKm())
                .mode(TravelMode.valueOf(this.getMode()))
                .build();

        travellog.calculateCO2();
        return travellog;
    }
}
