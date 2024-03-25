package com.dux.prueba_tecnica.dto.mapper;

import com.dux.prueba_tecnica.dto.request.CountryRequestDTO;
import com.dux.prueba_tecnica.dto.response.CountryResponseDTO;
import com.dux.prueba_tecnica.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mapping(source = "nombre", target = "name")
    Country requestToCountry(CountryRequestDTO countryRequestDTO);

    @Mapping(source = "name", target = "nombre")
    CountryResponseDTO countryToResponse(Country country);

    List<CountryResponseDTO> countryToResponseList(List<Country> countryList);
}
