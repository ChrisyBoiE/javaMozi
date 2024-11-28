package com.mozi.mozi.controller;

import com.mozi.mozi.model.Message;
import com.mozi.mozi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/messages")
    public String getAllMessages(Model model) {
        // Az összes üzenet lekérdezése fordított időrendben
        List<Message> messages = messageRepository.findAllByOrderByCreatedAtDesc();
        model.addAttribute("messages", messages);
        return "messages";
    }
}
