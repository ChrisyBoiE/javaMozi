package com.mozi.mozi.controller;

import com.mozi.mozi.repository.EloadasRepository;
import com.mozi.mozi.repository.FilmRepository;
import com.mozi.mozi.repository.MoziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@Controller
public class DataController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private EloadasRepository eloadasRepository;

    @Autowired
    private MoziRepository moziRepository;

    @GetMapping("/data")
    public String showData(Model model) {
        // Filmek lekérdezése
        try {
            model.addAttribute("films", filmRepository.findAll());
        } catch (Exception e) {
            model.addAttribute("films", Collections.emptyList());
        }

        // Előadások lekérdezése
        try {
            model.addAttribute("eloadasok", eloadasRepository.findAll());
        } catch (Exception e) {
            model.addAttribute("eloadasok", Collections.emptyList());
        }

        // Mozik lekérdezése
        try {
            model.addAttribute("mozik", moziRepository.findAll());
        } catch (Exception e) {
            model.addAttribute("mozik", Collections.emptyList());
        }

        return "data"; // data.html sablon
    }
}
