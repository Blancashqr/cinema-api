package com.blancash.cinemaapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.repos.CinemaRepo;
import com.blancash.cinemaapi.repos.MovieRepo;
import com.blancash.cinemaapi.repos.StaffRepo;
import com.blancash.cinemaapi.service.CinemaService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class CinemaServiceTest {

  @Mock
  CinemaRepo cinemaRepo;

  @Mock
  MovieRepo movieRepo;

  CinemaService cinemaService;

  @BeforeEach
  void setup() {
    cinemaRepo = mock(CinemaRepo.class);
    movieRepo = mock(MovieRepo.class);
    cinemaService = new CinemaService(cinemaRepo, movieRepo);
  }

  @Test
  void checkGetAllCinemas() {

    Cinema cinema = new Cinema(1, "Canary Wharf", new HashSet<>(), new ArrayList<>());
    List<Cinema> cinemaList = List.of(cinema);

    doReturn(cinemaList).when(cinemaRepo).findAll();

    cinemaService.getAllCinemas();

    verify(cinemaRepo).findAll();

  }

  @Test
  void checkGetCinemaById() {

    int id = 1;
    Cinema cinema = new Cinema(1, "Canary Wharf", new HashSet<>(), new ArrayList<>());

    doReturn(cinema).when(cinemaRepo).findCinemaById(anyInt());

    cinemaService.getCinemaById(id);

    verify(cinemaRepo).findCinemaById(id);

  }

  @Test
  void checkGetCinemasByMovieId() {

    int id = 1;
    Cinema cinema = new Cinema(1, "Canary Wharf", new HashSet<>(), new ArrayList<>());
    Set<Cinema> cinemaSet = Set.of(cinema);
    Movie movie = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, cinemaSet,
        new ArrayList<>());

    doReturn(movie).when(movieRepo).findMovieById(anyInt());

    Set<Cinema> result = cinemaService.getCinemasByMovieId(id);

    assertEquals(1, result.size());

    verify(movieRepo).findMovieById(id);

  }

  @Test
  void checkTotalDurationOfMoviesInCinema() {

    int id = 1;
    Movie movie1 = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180,
        new HashSet<>(), new ArrayList<>());
    Movie movie2 = new Movie(2, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 20,
        new HashSet<>(), new ArrayList<>());
    Set<Movie> movieSet = Set.of(movie1, movie2);
    Cinema cinema = new Cinema(1, "Canary Wharf", movieSet, new ArrayList<>());

    doReturn(cinema).when(cinemaRepo).findCinemaById(anyInt());

    int result = cinemaService.getTotalDurationOfMoviesInCinema(id);

    assertEquals(200, result);

    verify(cinemaRepo).findCinemaById(id);

  }

  @Test
  void checkAverageStaffSalary() {

    int id = 1;
    Staff staff1 = new Staff(1, "Lucia", 5000, new Cinema());
    Staff staff2 = new Staff(2, "Laura", 1000, new Cinema());
    List<Staff> staffList = List.of(staff1, staff2);
    Cinema cinema = new Cinema(id, "Nervion", new HashSet<>(), staffList);

    doReturn(cinema).when(cinemaRepo).findCinemaById(anyInt());

    double result = cinemaService.averageStaffSalary(id);

    assertEquals(3000, result);

    verify(cinemaRepo).findCinemaById(id);
  }


}
