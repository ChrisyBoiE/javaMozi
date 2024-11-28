package com.mozi.mozi.controller;

import com.mozi.mozi.model.User;
import com.mozi.mozi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        user.setRole("USER"); // Alapértelmezett szerep
        userService.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Sikeres regisztráció!");
        return response;
    }


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user, HttpSession session) {
        User foundUser = userService.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Felhasználó nem található!"));

        if (!foundUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Hibás jelszó!");
        }

        // Felhasználó és szerep eltárolása a session-ben
        session.setAttribute("user", foundUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Sikeres bejelentkezés!");
        response.put("role", foundUser.getRole());
        return response;
    }



    @PostMapping("/logout")
    public Map<String, String> logout(HttpSession session) {
        session.invalidate();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Sikeres kijelentkezés!");
        return response;
    }

    @GetMapping("/current-user")
    public User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
