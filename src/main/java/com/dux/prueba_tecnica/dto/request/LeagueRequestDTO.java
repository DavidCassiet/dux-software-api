package com.dux.prueba_tecnica.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LeagueRequestDTO {
    @NotBlank(message = "Debe ingresar un nombre")
    private String nombre;
    @NotBlank(message = "Debe ingresar un pais")
    private String pais;

    public LeagueRequestDTO() {
    }

    public LeagueRequestDTO(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
