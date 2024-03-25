package com.dux.prueba_tecnica.controller;

import com.dux.prueba_tecnica.dto.request.CountryRequestDTO;
import com.dux.prueba_tecnica.dto.response.CountryResponseDTO;
import com.dux.prueba_tecnica.exception.ResourceNotFoundException;
import com.dux.prueba_tecnica.service.CountryService;
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
@RequestMapping("/paises")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    @Operation(summary = "Crea un pais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pais creado exitosamente",
                    content = @Content(schema = @Schema(implementation = CountryResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "La solicitud es invalida")
    })
    public ResponseEntity<CountryResponseDTO> createCountry(@RequestBody @Valid CountryRequestDTO countryRequestDTO) {
        return new ResponseEntity<>(countryService.createCountry(countryRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/activos")
    @Operation(summary = "Obtiene todos los paises activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, lista de paises activos",
                    content = @Content(schema = @Schema(implementation = CountryResponseDTO.class)))
    })
    public ResponseEntity<List<CountryResponseDTO>> getAllActiveCountries() {
        return ResponseEntity.ok(countryService.findAllActiveCountries());
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los paiss")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, lista de paiss",
                    content = @Content(schema = @Schema(implementation = CountryResponseDTO.class)))
    })
    public ResponseEntity<List<CountryResponseDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.findAllCountries());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un pais por su ID",
            parameters = {@Parameter(name = "paisID", description = "ID del pais", example = "4")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, pais obtenido por ID",
                    content = @Content(schema = @Schema(implementation = CountryResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pais no encontrado")})
    public ResponseEntity<CountryResponseDTO> getCountryByID(@PathVariable("id") Long countryID) throws ResourceNotFoundException {
        return ResponseEntity.ok(countryService.findCountryById(countryID));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Obtiene todos los paises por nombre",
            parameters = {@Parameter(name = "nombre", description = "Nombre del pais", example = "España")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, lista de paiss obtenida por nombre",
                    content = @Content(schema = @Schema(implementation = CountryResponseDTO.class)))
    })
    public ResponseEntity<List<CountryResponseDTO>> getAllCountriesByName(@RequestParam(name = "nombre") String name) {
        return ResponseEntity.ok(countryService.findAllCountriesByName(name));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un pais",
            parameters = {@Parameter(name = "countryID", description = "ID del pais", example = "4")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa, devuelve la información del pais actualizada",
                    content = @Content(schema = @Schema(implementation = CountryResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Pais no encontrado")
    })
    public ResponseEntity<CountryResponseDTO> updateCountry(
            @PathVariable("id") Long countryID,
            @RequestBody @Valid CountryRequestDTO countryRequestDTO)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(countryService.updateCountry(countryID, countryRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Realiza un borrado logico de un pais",
            parameters = {@Parameter(name = "countryID", description = "ID del pais", example = "4")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pais borrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pais no encontrado")
    })
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") Long countryID) throws ResourceNotFoundException {
        countryService.deleteCountry(countryID);
        return ResponseEntity.noContent().build();
    }
}
