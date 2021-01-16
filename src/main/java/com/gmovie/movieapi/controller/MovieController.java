package com.gmovie.movieapi.controller;

import com.gmovie.movieapi.model.Movie;
import com.gmovie.movieapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/api/movies")
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
}
