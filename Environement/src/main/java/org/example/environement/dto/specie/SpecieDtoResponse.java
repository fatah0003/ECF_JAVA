package org.example.environement.dto.specie;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.environement.entity.enums.Category;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SpecieDtoResponse {
    private long id;
    private String commonName;
    private String scientificName;
    private String category;
}
