package com.gmovie.movieapi.unit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmovie.movieapi.model.Movie;
import com.gmovie.movieapi.repository.MovieRepository;
import com.gmovie.movieapi.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@JdbcTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class MovieServiceTest {

    @MockBean
    MovieRepository movieRepository;

    ObjectMapper mapper;

    String moviePath = "src/test/java/com/gmovie/movieapi/data/movies.json";
    List<Movie> movieList;

    @Test
    public void nothing() {

    }

    @BeforeEach
    void setUp() throws IOException {
        mapper = new ObjectMapper();

        File moviePath1 = new File(moviePath);
        movieList =  mapper.readValue(moviePath1, new TypeReference<ArrayList<Movie>>() {
        });


    }

    @Test
    public void serviceShouldReturnAllMovies() {
        when(movieRepository.findAll()).thenReturn(movieList);

        MovieService service = new MovieService(movieRepository);

        List<Movie> allMovies = service.getAllMovies();

        assertEquals(movieList, allMovies);
    }
}
