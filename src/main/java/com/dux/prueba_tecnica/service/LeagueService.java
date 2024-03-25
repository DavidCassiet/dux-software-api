package com.dux.prueba_tecnica.service;

import com.dux.prueba_tecnica.dto.request.LeagueRequestDTO;
import com.dux.prueba_tecnica.dto.response.LeagueResponseDTO;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;
import com.dux.prueba_tecnica.model.League;
import com.dux.prueba_tecnica.model.Team;

import java.util.List;

public interface LeagueService {
    LeagueResponseDTO createLeague(LeagueRequestDTO leagueRequestDTO) throws ResourceNotFoundException;
    List<LeagueResponseDTO> findAllActiveLeagues();

    List<LeagueResponseDTO> findAllLeagues();

    LeagueResponseDTO findLeagueById(Long leagueID) throws ResourceNotFoundException;

    List<LeagueResponseDTO> findAllLeaguesByName(String name);

    LeagueResponseDTO updateLeague(Long leagueID, LeagueRequestDTO leagueRequestDTO) throws ResourceNotFoundException;

    void deleteLeague(Long leagueID) throws ResourceNotFoundException;

    League findLeagueByName(String name) throws ResourceNotFoundException;

    void saveLeague(League objLeague);

    void addTeamToLeague(League objLeague, Team objTeam);

    void removeTeamFromLeague(League objLeague, Team objTeam);
}
