package org.example.repository;

import org.example.entity.Serv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью {@link org.example.entity.Serv}
 */
@Repository
public interface ServRepository extends JpaRepository<Serv, Long> {
}