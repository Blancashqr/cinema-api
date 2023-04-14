package com.blancash.cinemaapi.service;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.repos.CinemaRepo;
import com.blancash.cinemaapi.repos.MovieRepo;
import com.blancash.cinemaapi.repos.StaffRepo;
import com.blancash.cinemaapi.service.exception.EmptyMovieSetException;
import com.blancash.cinemaapi.service.exception.EmptyStaffListException;
import com.blancash.cinemaapi.service.exception.MovieNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class CinemaServiceTest {

    @Mock
    CinemaRepo cinemaRepo;

    @Mock
    MovieRepo movieRepo;

    @Mock
    StaffRepo staffRepo;

    CinemaService cinemaService;

    @BeforeEach
    void setup() {
        cinemaRepo = mock(CinemaRepo.class);
        movieRepo = mock(MovieRepo.class);
        staffRepo = mock(StaffRepo.class);
        cinemaService = new CinemaService(cinemaRepo, movieRepo, staffRepo);
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

    @Test
    void checkCreateNewCinema() throws EmptyMovieSetException, EmptyStaffListException {

        String name = "Nervion";

        Movie movie1 = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());
        Movie movie2 = new Movie(2, "Titanic", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());
        Set<Movie> movieSet = Set.of(movie1, movie2);
        List<Integer> movieIds = List.of(movie1.getId(), movie2.getId());

        Staff staff1 = new Staff(1, "Lucia", 25000, new Cinema());
        Staff staff2 = new Staff(2, "Manuel", 25000, new Cinema());
        List<Staff> staffList = List.of(staff1, staff2);
        List<Integer> staffIds = List.of(staff1.getId(), staff2.getId());

        List<List<Integer>> idList = List.of(movieIds, staffIds);

        doReturn(movieSet).when(movieRepo).findMovieByIdIn(anyList());

        doReturn(staffList).when(staffRepo).findStaffByIdIn(anyList());

        Cinema result = cinemaService.createNewCinema(name, idList);

        assertNotNull(result.getId());

        verify(cinemaRepo).save(result);

    }

    @Test
    void checkCreateNewCinemaMovieSetNull() {

        String name = "Nervion";

        List<Integer> movieIds = new ArrayList<>();

        Staff staff1 = new Staff(1, "Lucia", 25000, new Cinema());
        Staff staff2 = new Staff(2, "Manuel", 25000, new Cinema());
        List<Staff> staffList = List.of(staff1, staff2);
        List<Integer> staffIds = List.of(staff1.getId(), staff2.getId());

        List<List<Integer>> idList = List.of(movieIds, staffIds);

        doReturn(null).when(movieRepo).findMovieByIdIn(anyList());

        doReturn(staffList).when(staffRepo).findStaffByIdIn(anyList());

        EmptyMovieSetException thrown = Assertions.assertThrows(EmptyMovieSetException.class,
                () -> cinemaService.createNewCinema(name, idList));

        assertEquals("Set of movies is null or empty.", thrown.getMessage());

    }


    @Test
    void checkCreateNewCinemaStaffListNull() {

        String name = "Nervion";

        Movie movie1 = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());
        Movie movie2 = new Movie(2, "Titanic", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());
        Set<Movie> movieSet = Set.of(movie1, movie2);
        List<Integer> movieIds = List.of(movie1.getId(), movie2.getId());

        List<Integer> staffIds = new ArrayList<>();

        List<List<Integer>> idList = List.of(movieIds, staffIds);

        doReturn(movieSet).when(movieRepo).findMovieByIdIn(anyList());

        doReturn(null).when(staffRepo).findStaffByIdIn(anyList());

        EmptyStaffListException thrown = Assertions.assertThrows(EmptyStaffListException.class,
                () -> cinemaService.createNewCinema(name, idList));

        assertEquals("List of staff is null or empty.", thrown.getMessage());
    }

    @Test
    void checkCreateNewCinemaMovieSetEmpty() {

        String name = "Nervion";

        Set<Movie> movieSet = new HashSet<>();

        Staff staff1 = new Staff(1, "Lucia", 25000, new Cinema());
        Staff staff2 = new Staff(2, "Manuel", 25000, new Cinema());
        List<Staff> staffList = List.of(staff1, staff2);
        List<Integer> staffIds = List.of(staff1.getId(), staff2.getId());

        List<List<Integer>> idList = List.of(new ArrayList<>(), staffIds);

        doReturn(movieSet).when(movieRepo).findMovieByIdIn(anyList());

        doReturn(staffList).when(staffRepo).findStaffByIdIn(anyList());

        EmptyMovieSetException thrown = Assertions.assertThrows(EmptyMovieSetException.class,
                () -> cinemaService.createNewCinema(name, idList));

        assertEquals("Set of movies is null or empty.", thrown.getMessage());

    }

    @Test
    void checkCreateNewCinemaStaffListEmpty() {

        String name = "Nervion";

        Movie movie1 = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());
        Movie movie2 = new Movie(2, "Titanic", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());
        Set<Movie> movieSet = Set.of(movie1, movie2);
        List<Integer> movieIds = List.of(movie1.getId(), movie2.getId());

        List<Staff> staffList = new ArrayList<>();

        List<List<Integer>> idList = List.of(movieIds, new ArrayList<>());

        doReturn(movieSet).when(movieRepo).findMovieByIdIn(anyList());

        doReturn(staffList).when(staffRepo).findStaffByIdIn(anyList());

        EmptyStaffListException thrown = Assertions.assertThrows(EmptyStaffListException.class,
                () -> cinemaService.createNewCinema(name, idList));

        assertEquals("List of staff is null or empty.", thrown.getMessage());

    }

    @Test
    void checkAddNewMovie() throws MovieNotFoundException {

        int movieId = 14;
        int cinemaId = 2;

        Movie movie = new Movie(movieId, "Your name", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
                new ArrayList<>());
        Cinema cinema = new Cinema(cinemaId, "Nervion", new HashSet<>(), new ArrayList<>());

        doReturn(movie).when(movieRepo).findMovieById(anyInt());
        doReturn(cinema).when(cinemaRepo).findCinemaById(anyInt());

        Cinema result = cinemaService.addNewMovie(movieId, cinemaId);

        assertTrue(result.getMovies().contains(movie));


    }

    @Test
    void checkAddNewMovie_movieNotFound() {

        int movieId = 14;
        int cinemaId = 2;

        Cinema cinema = new Cinema(cinemaId, "Nervion", new HashSet<>(), new ArrayList<>());

        doReturn(null).when(movieRepo).findMovieById(anyInt());
        doReturn(cinema).when(cinemaRepo).findCinemaById(anyInt());

        MovieNotFoundException thrown = Assertions.assertThrows(MovieNotFoundException.class,
                () -> cinemaService.addNewMovie(movieId, cinemaId));

        assertEquals("Movie with id=14 not found.", thrown.getMessage());
    }


}
