package com.yogita.netflix.controller;

import com.yogita.netflix.model.Movie;
import com.yogita.netflix.model.User;
import com.yogita.netflix.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }

        // Fetch movies dynamically
        List<Movie> trending = movieService.trending();
        List<Movie> popular = movieService.popular();

        model.addAttribute("trendingMovies", trending);
        model.addAttribute("popularMovies", popular);

        return "home";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/tvshows")
    public String tv(){
        return "tvshows";
    }

    @GetMapping("/movies")
    public String movies(){
        return "movies";
    }

    @GetMapping("/mylist")
    public String list(){
        return "mylist";
    }

    @GetMapping("/premium")
    public String premium(){
        return "premium";
    }

    @GetMapping("/payment")
    public String payment(){
        return "payment";
    }
}