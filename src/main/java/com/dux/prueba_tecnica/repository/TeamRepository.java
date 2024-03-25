package com.dux.prueba_tecnica.repository;

import com.dux.prueba_tecnica.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByStatusTrue();

    List<Team> findAllByNameContainingIgnoreCaseAndStatusTrue(String name);

    Optional<Team> findByName(String name);
}
