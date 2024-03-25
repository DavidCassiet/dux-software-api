package com.dux.prueba_tecnica.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CountryRequestDTO {
    @NotBlank(message = "Debe ingresar un nombre")
    private String nombre;

    public CountryRequestDTO() {
    }

    public CountryRequestDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
