package com.dux.prueba_tecnica.repository;

import com.dux.prueba_tecnica.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

    Optional<League> findByName(String name);

    List<League> findAllByStatusTrue();

    List<League> findAllByNameContainingIgnoreCaseAndStatusTrue(String name);

    Optional<League> findByNameAndStatusTrue(String name);
}
