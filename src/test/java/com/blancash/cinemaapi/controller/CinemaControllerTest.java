package com.blancash.cinemaapi.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.service.CinemaService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class CinemaControllerTest {

  @Mock
  CinemaService cinemaService;

  CinemaController cinemaController;

  @BeforeEach
  void setup() {
    cinemaService = mock(CinemaService.class);
    cinemaController = new CinemaController(cinemaService);
  }

  @Test
  void checkGetAllCinemas() {

    Cinema cinema = new Cinema(1, "Canary Wharf", new HashSet<>(), new ArrayList<>());
    List<Cinema> cinemaList = List.of(cinema);

    doReturn(cinemaList).when(cinemaService).getAllCinemas();

    cinemaController.getAllCinemas();

    verify(cinemaService).getAllCinemas();

  }

  @Test
  void checkGetCinemasById() {

    int id = 1;
    Cinema cinema = new Cinema(1, "Canary Wharf", new HashSet<>(), new ArrayList<>());

    doReturn(cinema).when(cinemaService).getCinemaById(id);

    cinemaController.getCinemaById(id);

    verify(cinemaService).getCinemaById(id);

  }

  @Test
  void checkGetCinemasByMovieId() {

    int id = 1;
    Cinema cinema = new Cinema(1, "Canary Wharf", new HashSet<>(), new ArrayList<>());
    Set<Cinema> cinemaSet = Set.of(cinema);

    doReturn(cinemaSet).when(cinemaService).getCinemasByMovieId(id);

    cinemaController.getCinemasByMovieId(id);

    verify(cinemaService).getCinemasByMovieId(id);

  }

  @Test
  void checkGetTotalDurationOfMoviesInCinema() {

    int id = 1;
    int result = 200;

    doReturn(result).when(cinemaService).getTotalDurationOfMoviesInCinema(id);

    cinemaController.getTotalDurationOfMoviesInCinema(id);

    verify(cinemaService).getTotalDurationOfMoviesInCinema(id);

  }


}
