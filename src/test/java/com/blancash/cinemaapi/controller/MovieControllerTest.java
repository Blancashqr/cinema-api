package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MovieControllerTest {

    @Mock
    MovieService movieService;

    MovieController movieController;

    @BeforeEach
    void setup() {
        movieService = mock(MovieService.class);
        movieController = new MovieController(movieService);
    }

    @Test
    void checkGetAllMovies() {

        Movie movie = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());
        List<Movie> movieList = List.of(movie);

        doReturn(movieList).when(movieService).getAllMovies();

        movieController.getAllMovies();

        verify(movieService).getAllMovies();

    }

    @Test
    void checkGetMovieInMoreCinemas() {

        Movie movie = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());

        doReturn(movie).when(movieService).getMovieInMoreCinemas();

        movieController.getMovieInMoreCinemas();

        verify(movieService).getMovieInMoreCinemas();

    }

    @Test
    void checkGetMostWatchedMovie() {

        Movie movie = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());
        List<Movie> movieList = List.of(movie);

        doReturn(movieList).when(movieService).getMostWatchedMovie();

        movieController.getMostWatchedMovie();

        verify(movieService).getMostWatchedMovie();

    }

    @Test
    void checkGetMovieByIdOrNameWhenGivenId() {

        Optional<Integer> idOptional = Optional.of(1);
        Optional<String> nameOptional = Optional.empty();

        int id = 1;
        Movie movie = new Movie();

        doReturn(movie).when(movieService).getMovieById(anyInt());

        movieController.getMovieByIdOrName(idOptional, nameOptional);

        verify(movieService).getMovieById(id);

    }


    @Test
    void checkGetMovieByIdOrNameWhenNameGiven() {

        Optional<Integer> idOptional = Optional.empty();
        Optional<String> nameOptional = Optional.of("Shazam");

        String name = "Shazam";
        Movie movie = new Movie();

        doReturn(movie).when(movieService).getMovieByName(anyString());

        movieController.getMovieByIdOrName(idOptional, nameOptional);

        verify(movieService).getMovieByName(name);

    }

    @Test
    void checkGetMovieByIdOrNameNoIdOrNameGiven() {

        Optional<Integer> idOptional = Optional.empty();
        Optional<String> nameOptional = Optional.empty();

        Movie movie = movieController.getMovieByIdOrName(idOptional, nameOptional);

        assertNotNull(movie);

        verifyNoInteractions(movieService);

    }


}
