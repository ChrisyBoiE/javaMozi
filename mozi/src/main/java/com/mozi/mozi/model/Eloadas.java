package com.mozi.mozi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "eloadas")
public class Eloadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatikusan generált id
    @Column(name = "id")
    private Long id;

    @Column(name = "film_id") // Az oszlop pontos neve az adatbázisban
    private Long filmId;

    @Column(name = "mozi_id")
    private Long moziId;

    @Column(name = "datum")
    private String datum;

    @Column(name = "nezoszam")
    private Integer nezoszam;

    @Column(name = "bevetel")
    private Double bevetel;

    // Getterek és setterek
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getMoziId() {
        return moziId;
    }

    public void setMoziId(Long moziId) {
        this.moziId = moziId;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Integer getNezoszam() {
        return nezoszam;
    }

    public void setNezoszam(Integer nezoszam) {
        this.nezoszam = nezoszam;
    }

    public Double getBevetel() {
        return bevetel;
    }

    public void setBevetel(Double bevetel) {
        this.bevetel = bevetel;
    }
}
