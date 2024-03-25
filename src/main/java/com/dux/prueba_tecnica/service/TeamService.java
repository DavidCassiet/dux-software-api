package com.dux.prueba_tecnica.service;

import com.dux.prueba_tecnica.dto.request.TeamRequestDTO;
import com.dux.prueba_tecnica.dto.response.TeamResponseDTO;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;

import java.util.List;

public interface TeamService {
    TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO) throws ResourceNotFoundException;

    List<TeamResponseDTO> findAllActiveTeams();

    List<TeamResponseDTO> findAllTeams();

    TeamResponseDTO findTeamById(Long teamID) throws ResourceNotFoundException;

    List<TeamResponseDTO> findAllTeamsByName(String name);

    TeamResponseDTO updateTeam(Long teamID, TeamRequestDTO teamRequestDTO) throws ResourceNotFoundException;

    void deleteTeam(Long teamID) throws ResourceNotFoundException;
}
