package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.service.CinemaService;
import com.blancash.cinemaapi.service.exception.EmptyMovieSetException;
import com.blancash.cinemaapi.service.exception.EmptyStaffListException;
import com.blancash.cinemaapi.service.exception.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/cinemas")
    public List<Cinema> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/cinema/{id}")
    public Cinema getCinemaById(@PathVariable int id) {
        return cinemaService.getCinemaById(id);
    }

    @GetMapping("/cinema/movie/{id}")
    public Set<Cinema> getCinemasByMovieId(@PathVariable int id) {
        return cinemaService.getCinemasByMovieId(id);
    }

    @GetMapping("/cinema/movies/duration/{id}")
    public int getTotalDurationOfMoviesInCinema(@PathVariable int id) {
        return cinemaService.getTotalDurationOfMoviesInCinema(id);
    }

    @GetMapping("/average/staff/salary/{cinemaId}")
    public double getAverageStaffSalary(@PathVariable int cinemaId) {
        return cinemaService.averageStaffSalary(cinemaId);
    }

    @PostMapping("/cinema/{name}")
    public Cinema createNewCinema(@PathVariable String name, @RequestBody List<List<Integer>> idList)
            throws EmptyMovieSetException, EmptyStaffListException {
        return cinemaService.createNewCinema(name, idList);
    }

    @PostMapping("/cinema/new/movie/{movieId}/{cinemaId}")
    public Cinema addNewMovie(@PathVariable int movieId, @PathVariable int cinemaId) throws MovieNotFoundException {
        return cinemaService.addNewMovie(movieId, cinemaId);
    }


}
