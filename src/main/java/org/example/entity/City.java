package org.example.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.CityDTO;

import java.util.List;

/**
 * Сущность "Город".
 */
@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
public class City {
    /**
     * Идентификатор города.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название города.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Население города.
     */
    @Column(name = "population")
    private Integer population;

    /**
     * Флаг, указывающий, есть ли в городе метро.
     */
    @Column(name = "has_metro")
    private boolean hasMetro;

    /**
     * Список достопримечательностей, расположенных в городе.
     */
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attraction> attractions;

    /**
     * Обновляет данные города.
     *
     * @param dto данные для обновления
     * @return true, если данные были обновлены, false - в противном случае
     */
    public boolean update(CityDTO dto) {
        boolean updated = false;
        if (dto.getName() != null && !name.equals(dto.getName())) {
            name = dto.getName();
            updated = true;
        }
        if (dto.getPopulation() != null && !population.equals(dto.getPopulation())) {
            population = dto.getPopulation();
            updated = true;
        }
        if (hasMetro != dto.isHasMetro()) {
            hasMetro = dto.isHasMetro();
            updated = true;
        }
        return updated;
    }
}
