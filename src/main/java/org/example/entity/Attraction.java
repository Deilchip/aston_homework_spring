package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.AttractionType;
import org.example.dto.AttractionDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Сущность "Достопримечательность".
 */
@Entity
@Table(name = "attractions")
@Data
@NoArgsConstructor
public class Attraction {
    /**
     * Идентификатор достопримечательности.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название достопримечательности.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Дата создания достопримечательности.
     */
    @Column(name = "creation_date")
    private LocalDate creationDate;

    /**
     * Описание достопримечательности.
     */
    @Column(name = "description")
    private String description;

    /**
     * Тип достопримечательности.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AttractionType type;

    /**
     * Город, в котором находится достопримечательность.
     */
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    /**
     * Список услуг, предоставляемых в рамках достопримечательности.
     */
    @OneToMany(mappedBy = "attraction", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Serv> servs;

    /**
     * Обновляет данные достопримечательности.
     *
     * @param attraction данные для обновления
     * @return true, если данные были обновлены, false - в противном случае
     */
    public boolean update(AttractionDTO attraction) {
        boolean updated = false;
        if (attraction.getDescription() != null && !description.equals(attraction.getDescription())) {
            description = attraction.getDescription();
            updated = true;
        }
        return updated;
    }
}
