package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.service.CinemaService;
import com.blancash.cinemaapi.service.exception.EmptyMovieSetException;
import com.blancash.cinemaapi.service.exception.EmptyStaffListException;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/average/staff/salary/{cinemaId}" )
  public double getAverageStaffSalary(@PathVariable int cinemaId) {
    return cinemaService.averageStaffSalary(cinemaId);
  }

  @PostMapping("/cinema/new")
  public Cinema createNewCinema(@RequestParam String name, @RequestBody Set<Movie> movies, @RequestBody List<Staff> staffList)
      throws EmptyMovieSetException, EmptyStaffListException {
    return cinemaService.createNewCinema(name, movies, staffList);
  }


}
