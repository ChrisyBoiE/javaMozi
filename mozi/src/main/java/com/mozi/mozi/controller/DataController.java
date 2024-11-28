package com.mozi.mozi.controller;

import com.mozi.mozi.model.Film;
import com.mozi.mozi.model.Mozi;
import com.mozi.mozi.model.Eloadas;
import com.mozi.mozi.repository.FilmRepository;
import com.mozi.mozi.repository.MoziRepository;
import com.mozi.mozi.repository.EloadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private MoziRepository moziRepository;

    @Autowired
    private EloadasRepository eloadasRepository;

    @GetMapping("/data")
    public String showDataPage(Model model, HttpSession session) {
        // Ellenőrizzük, hogy van-e user a session-ben
        if (session.getAttribute("user") == null) {
            return "redirect:/login"; // Ha nincs bejelentkezve, irányítsuk át a login oldalra
        }

        // Adatok betöltése
        List<Film> films = filmRepository.findAll();
        List<Mozi> mozik = moziRepository.findAll();
        List<Eloadas> eloadasok = eloadasRepository.findAll();

        model.addAttribute("films", films);
        model.addAttribute("mozik", mozik);
        model.addAttribute("eloadasok", eloadasok);

        return "data"; // Visszatérünk az adatokat megjelenítő oldalra
    }
}
