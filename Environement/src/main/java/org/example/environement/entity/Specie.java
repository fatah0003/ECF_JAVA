package org.example.environement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.environement.dto.specie.SpecieDtoResponse;
import org.example.environement.entity.enums.Category;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Specie {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String commonName;
    @Column(nullable = false)
    private String scientificName;
    @Column(nullable = false)
    private Category category;

    public SpecieDtoResponse entityToDto (){
        return SpecieDtoResponse.builder()
                .id(this.getId())
                .category(this.getCategory().toString())
                .scientificName(this.getScientificName())
                .commonName(this.getCommonName())
                .build();
    }

}
