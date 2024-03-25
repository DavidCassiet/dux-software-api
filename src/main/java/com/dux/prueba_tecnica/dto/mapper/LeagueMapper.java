package com.dux.prueba_tecnica.dto.mapper;

import com.dux.prueba_tecnica.dto.request.LeagueRequestDTO;
import com.dux.prueba_tecnica.dto.response.LeagueResponseDTO;
import com.dux.prueba_tecnica.model.League;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface LeagueMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(target = "country", ignore = true)
    League requestToLeague(LeagueRequestDTO leagueRequestDTO);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "country.name", target = "pais")
    LeagueResponseDTO leagueToResponse(League league);

    List<LeagueResponseDTO> leagueToResponseList(List<League> leagueList);
}
