package com.mozi.mozi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "eloadas")
public class Eloadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "filmid", nullable = false)
    private Long filmid;

    @Column(name = "moziid", nullable = false)
    private Long moziid;

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

    public Long getFilmid() {
        return filmid;
    }

    public void setFilmid(Long filmid) {
        this.filmid = filmid;
    }

    public Long getMoziid() {
        return moziid;
    }

    public void setMoziid(Long moziid) {
        this.moziid = moziid;
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
