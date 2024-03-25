package com.dux.prueba_tecnica.repository;

import com.dux.prueba_tecnica.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);

    List<Country> findAllByStatusTrue();

    List<Country> findAllByNameContainingIgnoreCaseAndStatusTrue(String name);
}
