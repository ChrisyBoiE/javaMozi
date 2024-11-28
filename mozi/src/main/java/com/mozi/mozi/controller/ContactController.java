package com.mozi.mozi.controller;

import com.mozi.mozi.model.Message;
import com.mozi.mozi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String showContactForm(Model model) {
        model.addAttribute("message", new Message());
        return "contact";
    }

    @PostMapping
    public String submitContactForm(@ModelAttribute("message") Message message, Model model, HttpSession session) {
        // Debug: Ellenőrizd a session-ben tárolt user adatot
        Object user = session.getAttribute("user");
        System.out.println("Session user: " + user);

        // Ellenőrizd, hogy a mezők nincsenek üresen
        if (message.getName() == null || message.getEmail() == null ||
                message.getSubject() == null || message.getMessageContent() == null) {
            model.addAttribute("errorMessage", "Minden mezőt ki kell tölteni!");
            return "contact";
        }

        // Ellenőrizd, hogy a felhasználó be van-e jelentkezve
        if (user == null) {
            // Ha nincs bejelentkezve, adj hozzá a névhez "(Vendég)" kiegészítést
            message.setName(message.getName() + " (Vendég)");
        }

        // Debug: Ellenőrizd az elküldött message objektumot
        System.out.println("Message being saved: " + message);

        // Adat mentése
        messageRepository.save(message);

        // Sikeres üzenet visszaküldése
        model.addAttribute("successMessage", "Az üzenetedet sikeresen elküldtük!");
        return "contact";
    }

}
