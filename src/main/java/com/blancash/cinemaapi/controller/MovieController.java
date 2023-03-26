package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.service.MovieService;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping("/movies")
  public List<Movie> getAllMovies() {
    return movieService.getAllMovies();
  }

  @GetMapping("/movie/most/cinemas")
  public Movie getMovieInMoreCinemas() {
    return movieService.getMovieInMoreCinemas();
  }

  @GetMapping("/movie/most/watched")
  public List<Movie> getMostWatchedMovie() {
    return movieService.getMostWatchedMovie();
  }

  @GetMapping("/movie")
  public Movie getMovieByIdOrName(@RequestParam Optional<Integer> id, @RequestParam Optional<String> name) {
    if (id.isPresent()) {
      return movieService.getMovieById(id.get());
    } else if (name.isPresent()) {
      return movieService.getMovieByName(name.get());
    } else {
      return new Movie();
    }
  }



}


