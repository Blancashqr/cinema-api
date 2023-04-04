package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.service.CinemaService;
import com.blancash.cinemaapi.service.exception.EmptyMovieSetException;
import com.blancash.cinemaapi.service.exception.EmptyStaffListException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

        doReturn(cinema).when(cinemaService).getCinemaById(anyInt());

        cinemaController.getCinemaById(id);

        verify(cinemaService).getCinemaById(id);

    }

    @Test
    void checkGetCinemasByMovieId() {

        int id = 1;
        Cinema cinema = new Cinema(1, "Canary Wharf", new HashSet<>(), new ArrayList<>());
        Set<Cinema> cinemaSet = Set.of(cinema);

        doReturn(cinemaSet).when(cinemaService).getCinemasByMovieId(anyInt());

        cinemaController.getCinemasByMovieId(id);

        verify(cinemaService).getCinemasByMovieId(id);

    }

    @Test
    void checkGetTotalDurationOfMoviesInCinema() {

        int id = 1;
        int result = 200;

        doReturn(result).when(cinemaService).getTotalDurationOfMoviesInCinema(anyInt());

        cinemaController.getTotalDurationOfMoviesInCinema(id);

        verify(cinemaService).getTotalDurationOfMoviesInCinema(id);

    }


    @Test
    void checkGetAverageStaffSalary() {

        int id = 1;
        double result = 20000;

        doReturn(result).when(cinemaService).averageStaffSalary(anyInt());

        cinemaController.getAverageStaffSalary(id);

        verify(cinemaService).averageStaffSalary(id);

    }

    @Test
    void checkCreateNewCinema() throws EmptyMovieSetException, EmptyStaffListException {

        String name = "Nervion";
        List<List<Integer>> idList = new ArrayList<>();
        Cinema cinema = new Cinema();

        doReturn(cinema).when(cinemaService).createNewCinema(anyString(), anyList());

        cinemaController.createNewCinema(name, idList);

        verify(cinemaService).createNewCinema(name, idList);

    }

    @Test
    void checkCreateNewCinemaMovieSetEmpty() throws EmptyMovieSetException, EmptyStaffListException {

        String name = "Nervion";
        List<List<Integer>> idList = new ArrayList<>();

        doThrow(new EmptyMovieSetException("Set of movies is null or empty.")).when(cinemaService).
                createNewCinema(anyString(), anyList());

        EmptyMovieSetException thrown = Assertions.assertThrows(EmptyMovieSetException.class,
                () -> cinemaController.createNewCinema(name, idList));

        assertEquals("Set of movies is null or empty.", thrown.getMessage());

    }

    @Test
    void checkCreateNewCinemaStaffListEmpty() throws EmptyMovieSetException, EmptyStaffListException {

        String name = "Nervion";
        List<List<Integer>> idList = new ArrayList<>();

        doThrow(new EmptyStaffListException("List of staff is null or empty.")).when(cinemaService).
                createNewCinema(anyString(), anyList());

        EmptyStaffListException thrown = Assertions.assertThrows(EmptyStaffListException.class,
                () -> cinemaController.createNewCinema(name, idList));

        assertEquals("List of staff is null or empty.", thrown.getMessage());

    }


}
