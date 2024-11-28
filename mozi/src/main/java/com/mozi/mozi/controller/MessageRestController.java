package com.mozi.mozi.controller;

import com.mozi.mozi.model.Message;
import com.mozi.mozi.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    @Autowired
    private MessageRepository messageRepository;

    // Összes üzenet lekérése
    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    // Egy konkrét üzenet lekérése ID alapján
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Optional<Message> message = messageRepository.findById(id);
        return message.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Új üzenet létrehozása
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message savedMessage = messageRepository.save(message);
        return ResponseEntity.ok(savedMessage);
    }

    // Üzenet frissítése ID alapján
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage) {
        Optional<Message> existingMessage = messageRepository.findById(id);

        if (existingMessage.isPresent()) {
            Message message = existingMessage.get();
            message.setName(updatedMessage.getName());
            message.setEmail(updatedMessage.getEmail());
            message.setSubject(updatedMessage.getSubject());
            message.setMessageContent(updatedMessage.getMessageContent());
            messageRepository.save(message);
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Üzenet törlése ID alapján
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
