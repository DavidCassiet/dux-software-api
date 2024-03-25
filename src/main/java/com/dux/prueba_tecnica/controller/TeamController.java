package com.dux.prueba_tecnica.controller;

import com.dux.prueba_tecnica.dto.request.TeamRequestDTO;
import com.dux.prueba_tecnica.dto.response.TeamResponseDTO;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;
import com.dux.prueba_tecnica.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    @Operation(summary = "Crea un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipo creado exitosamente",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "La solicitud es invalida")
    })
    public ResponseEntity<TeamResponseDTO> createTeam(@RequestBody @Valid TeamRequestDTO teamRequestDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(teamService.createTeam(teamRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/activos")
    @Operation(summary = "Obtiene todos los equipos activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, lista de equipos activos",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class)))
    })
    public ResponseEntity<List<TeamResponseDTO>> getAllActiveTeams() {
        return ResponseEntity.ok(teamService.findAllActiveTeams());
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, lista de equipos",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class)))
    })
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.findAllTeams());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un equipo por su ID",
            parameters = {@Parameter(name = "teamID", description = "ID del equipo", example = "4")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, equipo obtenido por ID",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")})
    public ResponseEntity<TeamResponseDTO> getTeamByID(@PathVariable("id") Long teamID) throws ResourceNotFoundException {
        return ResponseEntity.ok(teamService.findTeamById(teamID));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Obtiene todos los equipos por nombre",
            parameters = {@Parameter(name = "nombre", description = "Nombre del equipo", example = "FC Barcelona")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, lista de equipos obtenida por nombre",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class)))
    })
    public ResponseEntity<List<TeamResponseDTO>> getAllTeamsByName(@RequestParam(name = "nombre") String name) {
        return ResponseEntity.ok(teamService.findAllTeamsByName(name));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un equipo",
            parameters = {@Parameter(name = "teamID", description = "ID del equipo", example = "4")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, devuelve la información del equipo actualizada",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    public ResponseEntity<TeamResponseDTO> updateTeam(
            @PathVariable("id") Long teamID,
            @RequestBody @Valid TeamRequestDTO teamRequestDTO)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(teamService.updateTeam(teamID, teamRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Realiza un borrado logico de un equipo",
            parameters = {@Parameter(name = "teamID", description = "ID del equipo", example = "4")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo borrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    public ResponseEntity<Void> deleteTeam(@PathVariable("id") Long teamID) throws ResourceNotFoundException {
        teamService.deleteTeam(teamID);
        return ResponseEntity.noContent().build();
    }
}
