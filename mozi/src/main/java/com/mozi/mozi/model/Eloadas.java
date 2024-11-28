package com.mozi.mozi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Eloadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate datum;
    private int nezoszam;
    private int bevetel;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "mozi_id")
    private Mozi mozi;

    // Getterek és Setterek
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getNezoszam() {
        return nezoszam;
    }

    public void setNezoszam(int nezoszam) {
        this.nezoszam = nezoszam;
    }

    public int getBevetel() {
        return bevetel;
    }

    public void setBevetel(int bevetel) {
        this.bevetel = bevetel;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Mozi getMozi() {
        return mozi;
    }

    public void setMozi(Mozi mozi) {
        this.mozi = mozi;
    }
}
