package org.example.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO для представления данных о {@link org.example.entity.Serv}.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ServDTO extends IdentifyDTO {
    /**
     * Название услуги.
     */
    private String name;

    /**
     * Описание услуги.
     */
    private String description;

    /**
     * Достопримечательность, в рамках которой предоставляется услуга.
     */
    @JsonBackReference
    private AttractionDTO attraction;
}
