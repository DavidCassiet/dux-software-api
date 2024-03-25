package com.dux.prueba_tecnica.controller;

import com.dux.prueba_tecnica.dto.request.LeagueRequestDTO;
import com.dux.prueba_tecnica.dto.response.LeagueResponseDTO;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;
import com.dux.prueba_tecnica.service.LeagueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ligas")
public class LeagueController {
    private final LeagueService ligaService;

    public LeagueController(LeagueService ligaService) {
        this.ligaService = ligaService;
    }

    @PostMapping
    @Operation(summary = "Crea una liga")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Liga creada exitosamente",
                    content = @Content(schema = @Schema(implementation = LeagueResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "La solicitud es invalida")
    })
    public ResponseEntity<LeagueResponseDTO> createLeague(@RequestBody LeagueRequestDTO leagueRequestDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(ligaService.createLeague(leagueRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/activas")
    @Operation(summary = "Obtiene todas las ligas activas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, lista de ligas activas",
                    content = @Content(schema = @Schema(implementation = LeagueResponseDTO.class)))
    })
    public ResponseEntity<List<LeagueResponseDTO>> getAllActiveLeagues() {
        return ResponseEntity.ok(ligaService.findAllActiveLeagues());
    }

    @GetMapping
    @Operation(summary = "Obtiene todas las ligas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, lista de ligas",
                    content = @Content(schema = @Schema(implementation = LeagueResponseDTO.class)))
    })
    public ResponseEntity<List<LeagueResponseDTO>> getAllLeagues() {
        return ResponseEntity.ok(ligaService.findAllLeagues());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una liga por su ID",
            parameters = {@Parameter(name = "leagueID", description = "ID de la liga", example = "4")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, liga obtenida por ID",
                    content = @Content(schema = @Schema(implementation = LeagueResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Liga no encontrada")})
    public ResponseEntity<LeagueResponseDTO> getLeagueByID(@PathVariable("id") Long leagueID) throws ResourceNotFoundException {
        return ResponseEntity.ok(ligaService.findLeagueById(leagueID));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Obtiene todas las ligas por nombre",
            parameters = {@Parameter(name = "nombre", description = "Nombre de la liga", example = "La Liga")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, lista de ligas obtenida por nombre",
                    content = @Content(schema = @Schema(implementation = LeagueResponseDTO.class)))
    })
    public ResponseEntity<List<LeagueResponseDTO>> getAllLeaguesByName(@RequestParam(name = "nombre") String name) {
        return ResponseEntity.ok(ligaService.findAllLeaguesByName(name));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una liga",
            parameters = {@Parameter(name = "leagueID", description = "ID del liga", example = "4")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, devuelve la información de la liga actualizada",
                    content = @Content(schema = @Schema(implementation = LeagueResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Liga no encontrada")
    })
    public ResponseEntity<LeagueResponseDTO> updateLeague(@PathVariable("id") Long leagueID, @RequestBody LeagueRequestDTO leagueRequestDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(ligaService.updateLeague(leagueID, leagueRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Realiza un borrado logico de una liga",
            parameters = {@Parameter(name = "leagueID", description = "ID del liga", example = "4")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liga borrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Liga no encontrada")
    })
    public ResponseEntity<Void> deleteLeague(@PathVariable("id") Long leagueID) throws ResourceNotFoundException {
        ligaService.deleteLeague(leagueID);
        return ResponseEntity.noContent().build();
    }
}
