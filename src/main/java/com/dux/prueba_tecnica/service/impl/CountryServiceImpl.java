package com.dux.prueba_tecnica.service.impl;

import com.dux.prueba_tecnica.dto.mapper.CountryMapper;
import com.dux.prueba_tecnica.dto.request.CountryRequestDTO;
import com.dux.prueba_tecnica.dto.response.CountryResponseDTO;
import com.dux.prueba_tecnica.exception.ErrorMessageKey;
import com.dux.prueba_tecnica.exception.ResourceAlreadyExistsException;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;
import com.dux.prueba_tecnica.model.Country;
import com.dux.prueba_tecnica.repository.CountryRepository;
import com.dux.prueba_tecnica.service.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    @Transactional
    public CountryResponseDTO createCountry(CountryRequestDTO countryRequestDTO) {
        countryRepository.findByName(countryRequestDTO.getNombre())
                .ifPresent(country -> {
                    if (country.isStatus())
                        throw new ResourceAlreadyExistsException(ErrorMessageKey.EXIST_DB.name(), "Pais");
                    else
                        throw new ResourceAlreadyExistsException(ErrorMessageKey.EXIST_DB_STATUS.name(), "Pais");
                });

        Country objCountry = countryMapper.requestToCountry(countryRequestDTO);
        objCountry.setStatus(true);
        countryRepository.save(objCountry);

        return countryMapper.countryToResponse(objCountry);
    }

    @Override
    public List<CountryResponseDTO> findAllActiveCountries() {
        return countryMapper.countryToResponseList(countryRepository.findAllByStatusTrue());
    }

    @Override
    public List<CountryResponseDTO> findAllCountries() {
        return countryMapper.countryToResponseList(countryRepository.findAll());
    }

    @Override
    public CountryResponseDTO findCountryById(Long countryID) throws ResourceNotFoundException {
        Country objCountry = findById(countryID);
        return countryMapper.countryToResponse(objCountry);
    }

    @Override
    public List<CountryResponseDTO> findAllCountriesByName(String name) {
        List<Country> countryList = countryRepository.findAllByNameContainingIgnoreCaseAndStatusTrue(name);
        return countryMapper.countryToResponseList(countryList);
    }

    @Override
    @Transactional
    public CountryResponseDTO updateCountry(Long countryID, CountryRequestDTO countryRequestDTO) throws ResourceNotFoundException {
        findById(countryID);
        Country objCountry = countryMapper.requestToCountry(countryRequestDTO);
        countryRepository.save(objCountry);

        return countryMapper.countryToResponse(objCountry);
    }

    @Override
    public void deleteCountry(Long countryID) throws ResourceNotFoundException {
        Country objCountry = findById(countryID);
        objCountry.setStatus(false);
        countryRepository.save(objCountry);
    }

    @Override
    public Country findCountryByName(String name) throws ResourceNotFoundException {
        return countryRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Pais"));
    }

    @Override
    public void saveCountry(Country objCountry) {
        countryRepository.save(objCountry);
    }

    private Country findById(Long countryID) throws ResourceNotFoundException {
        return countryRepository.findById(countryID).orElseThrow(() -> new ResourceNotFoundException("Pais"));
    }
}
