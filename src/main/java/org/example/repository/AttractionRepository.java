package org.example.repository;

import org.example.AttractionType;
import org.example.entity.Attraction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с сущностью {@link org.example.entity.Attraction}
 */
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    /**
     * Находит список достопримечательностей, расположенных в городе с указанным идентификатором.
     *
     * @param cityId идентификатор города
     * @return список достопримечательностей, расположенных в указанном городе
     */
    @EntityGraph(attributePaths = {"city", "servs"})
    Optional<List<Attraction>> findByCityId(Long cityId);

    /**
     * Находит список достопримечательностей указанного типа.
     *
     * @param type тип достопримечательности
     * @return список достопримечательностей указанного типа
     */
    @EntityGraph(attributePaths = {"servs"})
    Optional<List<Attraction>> findByType(AttractionType type);
}
