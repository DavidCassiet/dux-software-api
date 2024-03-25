package com.dux.prueba_tecnica.service.impl;

import com.dux.prueba_tecnica.dto.mapper.CountryMapper;
import com.dux.prueba_tecnica.dto.request.CountryRequestDTO;
import com.dux.prueba_tecnica.dto.response.CountryResponseDTO;
import com.dux.prueba_tecnica.exception.ResourceAlreadyExistsException;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;
import com.dux.prueba_tecnica.model.Country;
import com.dux.prueba_tecnica.repository.CountryRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceImplTest {

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryServiceImpl countryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testCreateCountry_Success() {
        CountryRequestDTO requestDTO = new CountryRequestDTO();
        requestDTO.setNombre("Argentina");
        Country country = new Country();
        country.setName("Argentina");
        country.setStatus(true);
        when(countryRepository.findByName("Argentina")).thenReturn(Optional.empty());
        when(countryMapper.requestToCountry(requestDTO)).thenReturn(country);
        when(countryMapper.countryToResponse(country)).thenReturn(new CountryResponseDTO());

        CountryResponseDTO responseDTO = countryService.createCountry(requestDTO);

        assertNotNull(responseDTO);
    }

    @Test(expected = ResourceAlreadyExistsException.class)
    public void testCreateCountry_AlreadyExists() {
        CountryRequestDTO requestDTO = new CountryRequestDTO();
        requestDTO.setNombre("Argentina");
        Country country = new Country();
        country.setName("Argentina");
        when(countryRepository.findByName("Argentina")).thenReturn(Optional.of(country));

        countryService.createCountry(requestDTO);
    }

    @Test
    public void testFindAllActiveCountries() {
        Country country = new Country();
        country.setName("Argentina");
        when(countryRepository.findAllByStatusTrue()).thenReturn(Collections.singletonList(country));
        when(countryMapper.countryToResponseList(Collections.singletonList(country)))
                .thenReturn(Collections.singletonList(new CountryResponseDTO()));

        List<CountryResponseDTO> responseDTOList = countryService.findAllActiveCountries();

        assertNotNull(responseDTOList);
        assertEquals(1, responseDTOList.size());
    }

    @Test
    public void testFindCountryById_Success() throws ResourceNotFoundException {
        Long countryID = 1L;
        Country country = new Country();
        country.setName("Argentina");
        when(countryRepository.findById(countryID)).thenReturn(Optional.of(country));
        when(countryMapper.countryToResponse(country)).thenReturn(new CountryResponseDTO());

        CountryResponseDTO responseDTO = countryService.findCountryById(countryID);

        assertNotNull(responseDTO);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindCountryById_NotFound() throws ResourceNotFoundException {
        Long countryID = 1L;
        when(countryRepository.findById(countryID)).thenReturn(Optional.empty());

        countryService.findCountryById(countryID);
    }

    @Test
    public void testFindAllCountriesByName() {
        String countryName = "Argentina";
        Country country = new Country();
        country.setName(countryName);
        when(countryRepository.findAllByNameContainingIgnoreCaseAndStatusTrue(countryName))
                .thenReturn(Collections.singletonList(country));
        when(countryMapper.countryToResponseList(Collections.singletonList(country)))
                .thenReturn(Collections.singletonList(new CountryResponseDTO()));

        List<CountryResponseDTO> responseDTOList = countryService.findAllCountriesByName(countryName);

        assertNotNull(responseDTOList);
        assertEquals(1, responseDTOList.size());
    }

    @Test
    public void testUpdateCountry_Success() throws ResourceNotFoundException {
        Long countryID = 1L;
        CountryRequestDTO requestDTO = new CountryRequestDTO();
        requestDTO.setNombre("Argentina");
        Country country = new Country();
        country.setName("Argentina");
        when(countryRepository.findById(countryID)).thenReturn(Optional.of(country));
        when(countryMapper.requestToCountry(requestDTO)).thenReturn(country);
        when(countryMapper.countryToResponse(country)).thenReturn(new CountryResponseDTO());

        CountryResponseDTO responseDTO = countryService.updateCountry(countryID, requestDTO);

        assertNotNull(responseDTO);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testUpdateCountry_NotFound() throws ResourceNotFoundException {
        Long countryID = 1L;
        CountryRequestDTO requestDTO = new CountryRequestDTO();
        requestDTO.setNombre("Argentina");
        when(countryRepository.findById(countryID)).thenReturn(Optional.empty());

        countryService.updateCountry(countryID, requestDTO);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteCountry_NotFound() throws ResourceNotFoundException {
        Long countryId = 1L;
        when(countryRepository.findById(countryId)).thenReturn(Optional.empty());

        countryService.deleteCountry(countryId);
    }

}
