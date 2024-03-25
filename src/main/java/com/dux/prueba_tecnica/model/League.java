package com.dux.prueba_tecnica.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean status;
    @OneToMany
    private List<Team> teamList;
    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public League() {
    }

    public League(Long id, String name, boolean status, List<Team> teamList, Country country) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.teamList = teamList;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
