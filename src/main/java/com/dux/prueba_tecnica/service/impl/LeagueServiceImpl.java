package com.dux.prueba_tecnica.service.impl;

import com.dux.prueba_tecnica.dto.mapper.LeagueMapper;
import com.dux.prueba_tecnica.dto.request.LeagueRequestDTO;
import com.dux.prueba_tecnica.dto.response.LeagueResponseDTO;
import com.dux.prueba_tecnica.exception.ErrorMessageKey;
import com.dux.prueba_tecnica.exception.InvalidResourceException;
import com.dux.prueba_tecnica.exception.ResourceAlreadyExistsException;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;
import com.dux.prueba_tecnica.model.Country;
import com.dux.prueba_tecnica.model.League;
import com.dux.prueba_tecnica.model.Team;
import com.dux.prueba_tecnica.repository.LeagueRepository;
import com.dux.prueba_tecnica.service.LeagueService;
import com.dux.prueba_tecnica.service.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class LeagueServiceImpl implements LeagueService {
    private final LeagueRepository leagueRepository;
    private final LeagueMapper leagueMapper;
    private final CountryService countryService;

    public LeagueServiceImpl(LeagueRepository leagueRepository, LeagueMapper leagueMapper, CountryService countryService) {
        this.leagueRepository = leagueRepository;
        this.leagueMapper = leagueMapper;
        this.countryService = countryService;
    }

    @Override
    @Transactional
    public LeagueResponseDTO createLeague(LeagueRequestDTO leagueRequestDTO) throws ResourceNotFoundException {
        leagueRepository.findByName(leagueRequestDTO.getNombre())
                .ifPresent(league -> {
                    if (league.isStatus())
                        throw new ResourceAlreadyExistsException(ErrorMessageKey.EXIST_DB.name(), "Liga");
                    else
                        throw new ResourceAlreadyExistsException(ErrorMessageKey.EXIST_DB_STATUS.name(), "Liga");
                });

        League objLeague = leagueMapper.requestToLeague(leagueRequestDTO);
        Country objCountry = countryService.findCountryByName(leagueRequestDTO.getPais());

        if (Objects.nonNull(objCountry.getLeague())) {
            throw new InvalidResourceException();
        }

        objLeague.setCountry(objCountry);
        objLeague.setStatus(true);
        leagueRepository.save(objLeague);

        objCountry.setLeague(objLeague);
        countryService.saveCountry(objCountry);

        return leagueMapper.leagueToResponse(objLeague);
    }

    @Override
    public List<LeagueResponseDTO> findAllActiveLeagues() {
        return leagueMapper.leagueToResponseList(leagueRepository.findAllByStatusTrue());
    }

    @Override
    public List<LeagueResponseDTO> findAllLeagues() {
        return leagueMapper.leagueToResponseList(leagueRepository.findAll());
    }

    @Override
    public LeagueResponseDTO findLeagueById(Long leagueID) throws ResourceNotFoundException {
        League objLeague = findById(leagueID);
        return leagueMapper.leagueToResponse(objLeague);
    }

    @Override
    public List<LeagueResponseDTO> findAllLeaguesByName(String name) {
        List<League> leagueList = leagueRepository.findAllByNameContainingIgnoreCaseAndStatusTrue(name);
        return leagueMapper.leagueToResponseList(leagueList);
    }

    @Override
    @Transactional
    public LeagueResponseDTO updateLeague(Long leagueID, LeagueRequestDTO leagueRequestDTO) throws ResourceNotFoundException {
        findById(leagueID);
        League objLeague = leagueMapper.requestToLeague(leagueRequestDTO);

        Country objCountry = countryService.findCountryByName(leagueRequestDTO.getPais());
        objLeague.setCountry(objCountry);

        objLeague.setId(leagueID);
        leagueRepository.save(objLeague);

        objCountry.setLeague(objLeague);
        countryService.saveCountry(objCountry);

        return leagueMapper.leagueToResponse(objLeague);
    }

    @Override
    public void deleteLeague(Long leagueID) throws ResourceNotFoundException {
        League objLeague = findById(leagueID);
        objLeague.setStatus(false);
        leagueRepository.save(objLeague);
    }

    @Override
    public League findLeagueByName(String name) throws ResourceNotFoundException {
        return leagueRepository.findByNameAndStatusTrue(name).orElseThrow(() -> new ResourceNotFoundException("Liga"));
    }

    @Override
    public void saveLeague(League objLeague) {
        leagueRepository.save(objLeague);
    }

    @Override
    public void addTeamToLeague(League objLeague, Team objTeam) {
        if (!objLeague.getTeamList().contains(objTeam)) {
            objLeague.getTeamList().add(objTeam);
        }
    }

    @Override
    public void removeTeamFromLeague(League objLeague, Team objTeam) {
        objLeague.getTeamList().remove(objTeam);
    }

    private League findById(Long leagueID) throws ResourceNotFoundException {
        return leagueRepository.findById(leagueID).orElseThrow(() -> new ResourceNotFoundException("Liga"));
    }
}
