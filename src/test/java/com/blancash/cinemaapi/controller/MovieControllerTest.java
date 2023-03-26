package com.blancash.cinemaapi.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.service.MovieService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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




}
