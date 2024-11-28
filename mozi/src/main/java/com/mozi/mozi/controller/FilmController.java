package com.mozi.mozi.controller;

import com.mozi.mozi.model.Film;
import com.mozi.mozi.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }
}
