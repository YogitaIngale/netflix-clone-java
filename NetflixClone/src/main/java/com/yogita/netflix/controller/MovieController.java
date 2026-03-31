package com.yogita.netflix.controller;

import com.yogita.netflix.model.Movie;
import com.yogita.netflix.model.User;
import com.yogita.netflix.service.MovieService;

import org.springframework.ui.Model; 
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller // Changed from @RestController to allow HTML redirects
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService; 

    @PostMapping("/add")
    public String addMovie(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam("posterFile") MultipartFile posterFile,
            @RequestParam String trailer,
            @RequestParam String category,
            HttpSession session,
            Model model) throws IOException {

        User admin = (User) session.getAttribute("user");
        
        // Null-safe role check
        if(admin == null || !"ADMIN".equals(admin.getRole())) {
            return "redirect:/admin/login";
        }

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setTrailer(trailer);
        movie.setCategory(category);

        if(!posterFile.isEmpty()){
            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;
            File uploadFolder = new File(uploadDir);
            if(!uploadFolder.exists()) uploadFolder.mkdirs();

            String filename = System.currentTimeMillis() + "_" + posterFile.getOriginalFilename();
            File destinationFile = new File(uploadDir + filename);
            
            posterFile.transferTo(destinationFile.toPath());
            movie.setPoster("/uploads/" + filename); 
        }

        movieService.addMovie(movie);
        
        return "redirect:/admin/dashboard?message=Movie+added+successfully";
    }

    @GetMapping("/trending")
    @ResponseBody 
    public Object trending(){
        return movieService.trending();
    }

    @GetMapping("/popular")
    @ResponseBody 
    public Object popular(){
        return movieService.popular();
    }
}