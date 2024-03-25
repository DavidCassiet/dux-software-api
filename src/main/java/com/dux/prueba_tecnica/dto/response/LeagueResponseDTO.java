package com.dux.prueba_tecnica.dto.response;

public class LeagueResponseDTO {
    private Long id;
    private String nombre;
    private String pais;

    public LeagueResponseDTO() {
    }

    public LeagueResponseDTO(Long id, String nombre, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
