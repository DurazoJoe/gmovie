package com.gmovie.movieapi.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmovie.movieapi.model.Movie;
import com.gmovie.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieRepository movieRepository;

    String moviePath = "src/test/java/com/gmovie/movieapi/data/movies.json";

    @Autowired
    ObjectMapper mapper;

    List<Movie> movieList;

    @BeforeEach
    void setUp() throws IOException {
        File moviePath1 = new File(moviePath);
        movieList =  mapper.readValue(moviePath1, new TypeReference<ArrayList<Movie>>() {
        });
    }

    private Movie getMovieByTitle(String title) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return new Movie();
    }

    @Test
    public void getAllMovies_returnsListOfMovies() throws Exception {

        when(movieRepository.findAll()).thenReturn(movieList);

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].title").value("The Avengers"));

    }

    @Test
    public void getMovieByTitle_ReturnsMovieDetails () throws Exception {

        Optional<Movie> expectedMovie = Optional.of(getMovieByTitle("Superman Returns"));

        when(movieRepository.findById("Superman Returns")).thenReturn(expectedMovie);

        mockMvc.perform(get("/api/movies/Superman Returns"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Superman Returns"))
                .andExpect(jsonPath("$.director").value("Bryan Singer"));
    }

    @Test
    public void getMovieByTitle_ReturnsNotFound () throws Exception {

        mockMvc.perform(get("/api/movies/bad hero"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("The movie you're looking for was not found"));
    }

}
