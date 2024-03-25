package com.dux.prueba_tecnica.service;

import com.dux.prueba_tecnica.dto.request.CountryRequestDTO;
import com.dux.prueba_tecnica.dto.response.CountryResponseDTO;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;
import com.dux.prueba_tecnica.model.Country;

import java.util.List;

public interface CountryService {
    CountryResponseDTO createCountry(CountryRequestDTO countryRequestDTO);

    List<CountryResponseDTO> findAllActiveCountries();

    List<CountryResponseDTO> findAllCountries();

    CountryResponseDTO findCountryById(Long countryID) throws ResourceNotFoundException;

    List<CountryResponseDTO> findAllCountriesByName(String name);

    CountryResponseDTO updateCountry(Long countryID, CountryRequestDTO countryRequestDTO) throws ResourceNotFoundException;

    void deleteCountry(Long countryID) throws ResourceNotFoundException;

    Country findCountryByName(String name) throws ResourceNotFoundException;

    void saveCountry(Country objCountry);
}
