package com.dux.prueba_tecnica.dto.request;

import jakarta.validation.constraints.NotBlank;

public class TeamRequestDTO {
    @NotBlank(message = "Debe ingresar un nombre")
    private String nombre;
    @NotBlank(message = "Debe ingresar una liga")
    private String liga;

    public TeamRequestDTO() {
    }

    public TeamRequestDTO(String nombre, String liga) {
        this.nombre = nombre;
        this.liga = liga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

}
