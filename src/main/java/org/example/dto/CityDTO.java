package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO для представления данных о {@link org.example.entity.City}.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO extends IdentifyDTO {
    /**
     * Название города.
     */
    private String name;

    /**
     * Население города.
     */
    private Integer population;

    /**
     * Флаг, указывающий, есть ли в городе метро.
     */
    private boolean hasMetro;
}
