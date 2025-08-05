package org.example.environement.dto.specie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.environement.entity.Specie;
import org.example.environement.entity.enums.Category;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SpecieDtoReceive {
    private String commonName;
    private String scientificName;
    private String category;

    public Specie dtoToEntity (){
        return Specie.builder()
                .category(Category.valueOf(this.getCategory()))
                .commonName(this.getCommonName())
                .scientificName(this.getScientificName())
                .build();
    }
}
