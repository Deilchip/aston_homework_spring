package org.example.repository;

import org.example.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью {@link org.example.entity.City}
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
