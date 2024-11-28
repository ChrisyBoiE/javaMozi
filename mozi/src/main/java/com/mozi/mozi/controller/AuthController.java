package com.mozi.mozi.controller;

import com.mozi.mozi.model.User;
import com.mozi.mozi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller // Visszatérési értékek JSON válaszok
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Regisztráció
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        user.setRole("USER"); // Alapértelmezett szerep
        userService.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Sikeres regisztráció!");
        return ResponseEntity.ok(response);
    }

    // Bejelentkezés
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user, HttpSession session) {
        User foundUser = userService.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található!"));

        if (!foundUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Hibás jelszó!");
        }

        // Felhasználó mentése a session-be
        session.setAttribute("user", foundUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Sikeres bejelentkezés!");
        response.put("role", foundUser.getRole());
        return ResponseEntity.ok(response);
    }

    // Kijelentkezés
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Session érvénytelenítése
        session.invalidate();
        // Átirányítás a főoldalra
        return "redirect:/";
    }

    // Aktuális felhasználó lekérdezése
    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build(); // Ha nincs bejelentkezve
        }
        return ResponseEntity.ok(user);
    }
}
