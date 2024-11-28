package com.mozi.mozi.controller;

import com.mozi.mozi.model.User;
import com.mozi.mozi.repository.EloadasRepository;
import com.mozi.mozi.repository.FilmRepository;
import com.mozi.mozi.repository.MoziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private EloadasRepository eloadasRepository;

    @Autowired
    private MoziRepository moziRepository;

    @GetMapping("/data")
    public String showData(Model model, HttpSession session) {
        try {
            // Ellenőrizzük, hogy a felhasználó be van-e jelentkezve
            User user = (User) session.getAttribute("user");
            if (user == null) {
                logger.error("Nincs bejelentkezve felhasználó.");
                return "redirect:/login"; // Ha nincs bejelentkezve, irányítsuk át
            }

            logger.info("Felhasználó bejelentkezve: " + user.getUsername());

            // Adatok lekérdezése
            model.addAttribute("films", filmRepository.findAll());
            model.addAttribute("eloadasok", eloadasRepository.findAll());
            model.addAttribute("mozik", moziRepository.findAll());

            return "data"; // Tovább a data.html sablonhoz
        } catch (Exception e) {
            logger.error("Hiba történt az adatok megjelenítése közben", e);
            return "error"; // Hibaoldal, ha valami nem működik
        }
    }
}
