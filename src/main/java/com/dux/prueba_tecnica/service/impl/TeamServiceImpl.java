package com.dux.prueba_tecnica.service.impl;

import com.dux.prueba_tecnica.dto.mapper.TeamMapper;
import com.dux.prueba_tecnica.dto.request.TeamRequestDTO;
import com.dux.prueba_tecnica.dto.response.TeamResponseDTO;
import com.dux.prueba_tecnica.exception.ErrorMessageKey;
import com.dux.prueba_tecnica.exception.ResourceAlreadyExistsException;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;
import com.dux.prueba_tecnica.model.Team;
import com.dux.prueba_tecnica.model.League;
import com.dux.prueba_tecnica.repository.TeamRepository;
import com.dux.prueba_tecnica.service.TeamService;
import com.dux.prueba_tecnica.service.LeagueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final LeagueService leagueService;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper, LeagueService leagueService) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.leagueService = leagueService;
    }

    @Override
    @Transactional
    public TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO) throws ResourceNotFoundException {
        teamRepository.findByName(teamRequestDTO.getNombre())
                .ifPresent(equipo -> {
                    if (equipo.isStatus())
                        throw new ResourceAlreadyExistsException(ErrorMessageKey.EXIST_DB.name(), "Equipo");
                    else
                        throw new ResourceAlreadyExistsException(ErrorMessageKey.EXIST_DB_STATUS.name(), "Equipo");
                });

        Team objTeam = teamMapper.requestToTeam(teamRequestDTO);
        League objLeague = leagueService.findLeagueByName(teamRequestDTO.getLiga());

        objTeam.setLeague(objLeague);
        objTeam.setStatus(true);
        teamRepository.save(objTeam);

        leagueService.addTeamToLeague(objLeague, objTeam);
        leagueService.saveLeague(objLeague);

        return teamMapper.teamToResponse(objTeam);
    }

    @Override
    public List<TeamResponseDTO> findAllActiveTeams() {
        return teamMapper.teamToResponseList(teamRepository.findAllByStatusTrue());
    }

    @Override
    public List<TeamResponseDTO> findAllTeams() {
        return teamMapper.teamToResponseList(teamRepository.findAll());
    }

    @Override
    public TeamResponseDTO findTeamById(Long teamID) throws ResourceNotFoundException {
        Team objTeam = findById(teamID);

        return teamMapper.teamToResponse(objTeam);
    }

    @Override
    public List<TeamResponseDTO> findAllTeamsByName(String name) {
        return teamMapper.teamToResponseList(teamRepository.findAllByNameContainingIgnoreCaseAndStatusTrue(name));
    }

    @Override
    @Transactional
    public TeamResponseDTO updateTeam(Long equipoId, TeamRequestDTO teamRequestDTO) throws ResourceNotFoundException {
        Team objPreviousTeam = findById(equipoId);
        League objPreviousLeague = objPreviousTeam.getLeague();

        Team objTeam = teamMapper.requestToTeam(teamRequestDTO);
        objTeam.setId(equipoId);

        League objLeague = leagueService.findLeagueByName(teamRequestDTO.getLiga());

        if (!objPreviousLeague.getId().equals(objLeague.getId())) {
            leagueService.removeTeamFromLeague(objPreviousLeague, objPreviousTeam);
        }

        objTeam.setLeague(objLeague);
        teamRepository.save(objTeam);

        leagueService.addTeamToLeague(objLeague, objTeam);
        leagueService.saveLeague(objLeague);

        return teamMapper.teamToResponse(objTeam);
    }

    @Override
    public void deleteTeam(Long teamID) throws ResourceNotFoundException {
        Team objTeam = findById(teamID);
        objTeam.setStatus(false);

        teamRepository.save(objTeam);
    }

    private Team findById(Long teamID) throws ResourceNotFoundException {
        return teamRepository.findById(teamID).orElseThrow(() -> new ResourceNotFoundException("equipo"));
    }
}
