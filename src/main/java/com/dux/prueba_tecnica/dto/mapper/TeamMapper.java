package com.dux.prueba_tecnica.dto.mapper;

import com.dux.prueba_tecnica.dto.request.TeamRequestDTO;
import com.dux.prueba_tecnica.dto.response.TeamResponseDTO;
import com.dux.prueba_tecnica.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(target = "league", ignore = true)
    Team requestToTeam(TeamRequestDTO teamRequestDTO);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "league.name", target = "liga")
    @Mapping(source = "league.country.name", target = "pais")
    TeamResponseDTO teamToResponse(Team team);

    List<TeamResponseDTO> teamToResponseList(List<Team> teamList);
}
