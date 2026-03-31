package com.yogita.netflix.controller;

import com.yogita.netflix.model.User;
import com.yogita.netflix.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

 
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        service.register(user);
        return ResponseEntity.ok("Registered");
    }

   
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body,
                                   HttpSession session){
        String email = body.get("email");
        String password = body.get("password");

        User u = service.login(email, password);

        if(u != null){
            session.setAttribute("user", u);
            return ResponseEntity.ok(u); 
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body("Invalid email or password");
    }

    
    @GetMapping("/check")
    public boolean check(HttpSession session){
        return session.getAttribute("user") != null;
    }
}