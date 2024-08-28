package org.example.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.AttractionType;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO для представления данных о {@link org.example.entity.Attraction}.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDTO extends IdentifyDTO {
    /**
     * Название достопримечательности.
     */
    private String name;

    /**
     * Дата создания достопримечательности.
     */
    private LocalDate creationDate;

    /**
     * Описание достопримечательности.
     */
    private String description;

    /**
     * Тип достопримечательности.
     */
    private AttractionType type;

    /**
     * Список услуг, предоставляемых в рамках достопримечательности.
     */
    @JsonManagedReference
    private List<ServDTO> servs;
    private CityDTO city;
}
