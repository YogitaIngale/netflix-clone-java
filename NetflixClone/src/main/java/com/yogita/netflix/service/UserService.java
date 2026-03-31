package com.yogita.netflix.service;

import com.yogita.netflix.model.User;
import com.yogita.netflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Register new user
    public User register(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setPremium(false);
        return repo.save(user);
    }

    // Login (users from DB + hardcoded admin)
    public User login(String email, String password){

        // === HARD-CODED ADMIN LOGIN ===
        if("admin@gmail.com".equalsIgnoreCase(email) && "123".equals(password)){
            User admin = new User();
            admin.setId(0L); // placeholder, not stored in DB
            admin.setName("Admin");
            admin.setEmail(email);
            admin.setRole("ADMIN");
            admin.setPremium(false);
            return admin;
        }

        // === NORMAL USER LOGIN ===
        User u = repo.findByEmail(email);
        if(u != null && encoder.matches(password, u.getPassword()))
            return u;

        return null;
    }

    // Save user (optional)
    public void save(User user){
        repo.save(user);
    }
}