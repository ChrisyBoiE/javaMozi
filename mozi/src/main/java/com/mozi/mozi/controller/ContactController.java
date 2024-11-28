package com.mozi.mozi.controller;

import com.mozi.mozi.model.Message;
import com.mozi.mozi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String submitContactForm(@ModelAttribute("message") Message message, Model model) {
        // Egyszerű validáció
        if (message.getName() == null || message.getEmail() == null ||
                message.getSubject() == null || message.getMessageContent() == null) {
            model.addAttribute("errorMessage", "Minden mezőt ki kell tölteni!");
            return "contact";
        }

        // Adatok mentése az adatbázisba
        messageRepository.save(message);

        // Sikeres visszajelzés
        model.addAttribute("successMessage", "Az üzenet sikeresen elküldve!");
        return "contact";
    }
}
