package com.yogita.netflix.service;

import com.yogita.netflix.model.Movie;
import com.yogita.netflix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repo;

    // Add a new movie
    public Movie addMovie(Movie m) {
        return repo.save(m);
    }

    // Delete a movie by ID
    public void deleteMovie(Integer id) {
        repo.deleteById(id);
    }

    // Get all movies (admin dashboard)
    public List<Movie> getAllMovies() {
        return repo.findAll();
    }

    // Trending movies
    public List<Movie> trending() {
        return repo.findByCategory("TRENDING");
    }

    // Popular movies
    public List<Movie> popular() {
        return repo.findByCategory("POPULAR");
    }
}