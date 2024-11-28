package com.mozi.mozi.controller;

import com.mozi.mozi.model.Message;
import com.mozi.mozi.model.User;
import com.mozi.mozi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/messages")
    public String getAllMessages(Model model, HttpSession session) {
        try {
            // Ellenőrizzük, hogy a felhasználó be van-e jelentkezve
            User user = (User) session.getAttribute("user");
            if (user == null) {
                logger.error("Nincs bejelentkezve felhasználó.");
                return "redirect:/login"; // Ha nincs bejelentkezve, irányítsuk át
            }

            // Ellenőrizzük, hogy az ADMIN szerepkörű-e a felhasználó
            if (!"ADMIN".equals(user.getRole())) {
                logger.error("A felhasználónak nincs jogosultsága megtekinteni az üzeneteket: " + user.getUsername());
                return "error"; // Hibaoldal nem jogosult felhasználóknak
            }

            logger.info("ADMIN felhasználó bejelentkezve: " + user.getUsername());

            // Az összes üzenet lekérdezése fordított időrendben
            List<Message> messages = messageRepository.findAllByOrderByCreatedAtDesc();
            model.addAttribute("messages", messages);

            return "messages"; // Tovább a messages.html sablonhoz
        } catch (Exception e) {
            logger.error("Hiba történt az üzenetek megjelenítése közben", e);
            return "error"; // Hibaoldal, ha valami nem működik
        }
    }
}
