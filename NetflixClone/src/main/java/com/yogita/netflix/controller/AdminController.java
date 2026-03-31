package com.yogita.netflix.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.yogita.netflix.model.Movie;
import com.yogita.netflix.model.User;
import com.yogita.netflix.service.MovieService;
import com.yogita.netflix.service.UploadLogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UploadLogService logService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model,
                            @RequestParam(value="message", required=false) String message) {
        User admin = (User) session.getAttribute("user");
        if(admin == null || !"ADMIN".equals(admin.getRole())) {
            return "redirect:/admin/login";
        }

        model.addAttribute("movies", movieService.getAllMovies());
        if (message != null) model.addAttribute("message", message);
        return "admin"; 
    }

    @PostMapping("/add")
    @ResponseBody 
    public ResponseEntity<String> addMovie(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam("posterFile") MultipartFile posterFile,
            @RequestParam String trailer,
            @RequestParam String category,
            HttpSession session) throws IOException {

        User admin = (User) session.getAttribute("user");
        if(admin == null || !"ADMIN".equals(admin.getRole())) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setTrailer(trailer);
        movie.setCategory(category);

        if(!posterFile.isEmpty()){
            String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";
            File uploadFolder = new File(uploadDir);
            if(!uploadFolder.exists()) uploadFolder.mkdirs();

            String filename = System.currentTimeMillis() + "_" + posterFile.getOriginalFilename();
            Path destinationPath = Paths.get(uploadDir).resolve(filename);
            Files.copy(posterFile.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            movie.setPoster("/uploads/" + filename); 
        }

      
        Movie savedMovie = movieService.addMovie(movie);
        
       
        logService.logUpload(admin.getId().intValue(), savedMovie.getId());
        
        return ResponseEntity.ok("Movie added successfully!");
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Integer id, HttpSession session){
        User admin = (User) session.getAttribute("user");
        if(admin == null || !"ADMIN".equals(admin.getRole())) return "redirect:/admin/login";

        movieService.deleteMovie(id);
        return "redirect:/admin/dashboard?message=Movie+deleted+successfully";
    }
}