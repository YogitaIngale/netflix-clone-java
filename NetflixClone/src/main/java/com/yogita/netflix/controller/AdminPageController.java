package com.yogita.netflix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    // Serve admin login page
    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin_login"; // this is the name of your HTML template (admin_login.html)
    }
}