package com.mozi.mozi.repository;

import com.mozi.mozi.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}

