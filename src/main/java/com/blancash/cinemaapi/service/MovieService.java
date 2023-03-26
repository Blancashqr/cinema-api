package com.blancash.cinemaapi.service;

import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.repos.MovieRepo;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  private final MovieRepo movieRepo;

  @Autowired
  public MovieService(MovieRepo movieRepo) {
    this.movieRepo = movieRepo;
  }

  public List<Movie> getAllMovies() {
    return movieRepo.findAll();
  }

  public Movie getMovieInMoreCinemas() {

    List<Movie> movieList = movieRepo.findAll();

    return movieList.stream()
        .max(Comparator.comparingInt(value -> value.getCinemas().size()))
        .get();

  }

  public List<Movie> getMostWatchedMovie() {

    List<Movie> movieList = movieRepo.findAll();

    int biggestNumberOfTickets = movieList.stream()
        .mapToInt(value -> value.getTickets().size())
        .max()
        .getAsInt();

    return movieList.stream()
        .filter(movie -> movie.getTickets().size() == biggestNumberOfTickets)
        .toList();
  }

  public Movie getMovieByName(String name) {
    return movieRepo.findMovieByName(name);
  }

  public Movie getMovieById(int id) {
    return movieRepo.findMovieById(id);
  }

}
