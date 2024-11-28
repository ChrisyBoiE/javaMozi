package com.mozi.mozi.controller;

import com.mozi.mozi.model.Eloadas;
import com.mozi.mozi.model.Film;
import com.mozi.mozi.model.Mozi;
import com.mozi.mozi.model.User;
import com.mozi.mozi.repository.EloadasRepository;
import com.mozi.mozi.repository.FilmRepository;
import com.mozi.mozi.repository.MoziRepository;
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
    private EloadasRepository eloadasRepository;

    @Autowired
    private MoziRepository moziRepository;

    @GetMapping("/data")
    public String showData(Model model, HttpSession session) {
        // Ellenőrizd, hogy a felhasználó be van-e jelentkezve
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // Ha nincs bejelentkezve, irányítsd át a login oldalra
            return "redirect:/login";
        }

        try {
            // Filmek lekérdezése
            List<Film> films = filmRepository.findAll();
            model.addAttribute("films", films);

            // Előadások lekérdezése
            List<Eloadas> eloadasok = eloadasRepository.findAll();
            model.addAttribute("eloadasok", eloadasok);

            // Mozik lekérdezése
            List<Mozi> mozik = moziRepository.findAll();
            model.addAttribute("mozik", mozik);

            return "data"; // data.html sablon
        } catch (Exception e) {
            // Hiba esetén irányítsd át a hibaoldalra
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}
