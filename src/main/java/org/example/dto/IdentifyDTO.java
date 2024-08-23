package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Абстрактный класс, представляющий базовый DTO для идентифицируемых сущностей.
 * Содержит поле для хранения идентификатора сущности.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class IdentifyDTO {
    /**
     * Идентификатор сущности.
     */
    private Long id;
}