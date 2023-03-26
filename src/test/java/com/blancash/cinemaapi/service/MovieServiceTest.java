package com.blancash.cinemaapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Ticket;
import com.blancash.cinemaapi.repos.MovieRepo;
import com.blancash.cinemaapi.service.MovieService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class MovieServiceTest {

  @Mock
  MovieRepo movieRepo;

  MovieService movieService;

  @BeforeEach
  void setup() {
    movieRepo = mock(MovieRepo.class);
    movieService = new MovieService(movieRepo);
  }

  @Test
  void checkGetAllMovies() {

    Movie movie = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        new ArrayList<>());
    List<Movie> movieList = List.of(movie);

    doReturn(movieList).when(movieRepo).findAll();

    movieService.getAllMovies();

    verify(movieRepo).findAll();

  }

  @Test
  void checkGetMovieInMoreCinemas() {

    Cinema cinema1 = new Cinema(1, "Canary Wharf", new HashSet<>(), new ArrayList<>());
    Set<Cinema> cinemaSet1 = Set.of(cinema1);
    Movie movie1 = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, cinemaSet1,
        new ArrayList<>());
    Set<Cinema> cinemaSet2 = new HashSet<>();
    Movie movie2 = new Movie(2, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, cinemaSet2,
        new ArrayList<>());
    List<Movie> movieList = List.of(movie1, movie2);

    doReturn(movieList).when(movieRepo).findAll();

    Movie result = movieService.getMovieInMoreCinemas();

    assertEquals(1, result.getId());

    verify(movieRepo).findAll();

  }

  @Test
  void checkGetMostWatchedMovie() {

    Ticket ticket1 = new Ticket(1, 2, 23, new Movie(), new Client());
    List<Ticket> ticketList = List.of(ticket1);
    Movie movie1 = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        ticketList);
    Movie movie2 = new Movie(2, "Narnia", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        new ArrayList<>());

    List<Movie> movieList = List.of(movie1, movie2);

    doReturn(movieList).when(movieRepo).findAll();

    List<Movie> result = movieService.getMostWatchedMovie();

    assertEquals(1, result.size());
    assertEquals(1, result.get(0).getId());

    verify(movieRepo).findAll();

  }

  @Test
  void checkGetMostWatchedMovieMoreThanOne() {

    Ticket ticket1 = new Ticket(1, 2, 23, new Movie(), new Client());
    List<Ticket> ticketList = List.of(ticket1);
    Movie movie1 = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        ticketList);
    Movie movie2 = new Movie(2, "Narnia", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        ticketList);
    Movie movie3 = new Movie(3, "Titanic", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        new ArrayList<>());

    List<Movie> movieList = List.of(movie1, movie2, movie3);

    doReturn(movieList).when(movieRepo).findAll();

    List<Movie> result = movieService.getMostWatchedMovie();

    assertEquals(2, result.size());
    assertEquals(1, result.get(0).getId());
    assertEquals(2, result.get(1).getId());

    verify(movieRepo).findAll();

  }




}
