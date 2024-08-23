package org.example.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.ServDTO;

/**
 * Сущность "Услуга".
 */
@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
public class Serv {
    /**
     * Идентификатор услуги.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название услуги.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Описание услуги.
     */
    @Column(name = "description")
    private String description;

    /**
     * Достопримечательность, в рамках которой предоставляется услуга.
     */
    @ManyToOne
    @JoinColumn(name = "attraction_id", nullable = false)
    private Attraction attraction;

    /**
     * Обновляет данные услуги.
     *
     * @param servDTO данные для обновления
     * @return true, если данные были обновлены, false - в противном случае
     */
    public boolean update(ServDTO servDTO) {
        boolean updated = false;
        if (servDTO.getName() != null && !name.equals(servDTO.getName())) {
            name = servDTO.getName();
            updated = true;
        }
        if (servDTO.getDescription() != null && !description.equals(servDTO.getDescription())) {
            description = servDTO.getDescription();
            updated = true;
        }
        if (servDTO.getAttraction().getId() != null && !attraction.getId().equals(servDTO.getAttraction().getId())) {
            attraction.setId(servDTO.getAttraction().getId());
            updated = true;
        }
        return updated;
    }
}
