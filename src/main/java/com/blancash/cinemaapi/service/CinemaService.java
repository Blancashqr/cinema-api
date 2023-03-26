package com.blancash.cinemaapi.service;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.repos.CinemaRepo;
import com.blancash.cinemaapi.repos.MovieRepo;
import com.blancash.cinemaapi.repos.StaffRepo;
import com.blancash.cinemaapi.service.exception.EmptyMovieSetException;
import com.blancash.cinemaapi.service.exception.EmptyStaffListException;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CinemaService {

  private final CinemaRepo cinemaRepo;
  private final MovieRepo movieRepo;

  @Autowired
  public CinemaService(CinemaRepo cinemaRepo, MovieRepo movieRepo) {
    this.cinemaRepo = cinemaRepo;
    this.movieRepo = movieRepo;
  }

  public List<Cinema> getAllCinemas() {
    return cinemaRepo.findAll();
  }

  public Cinema getCinemaById(int id) {
    return cinemaRepo.findCinemaById(id);
  }

  public Set<Cinema> getCinemasByMovieId(int id) {

    Movie movie = movieRepo.findMovieById(id);

    return movie.getCinemas();

  }

  public int getTotalDurationOfMoviesInCinema(int id) {

    Cinema cinema = cinemaRepo.findCinemaById(id);

    Set<Movie> movieList = cinema.getMovies();

    return movieList.stream()
        .mapToInt(Movie::getDuration)
        .sum();
  }

  public double averageStaffSalary(int cinemaId) {
    log.info("Checking average staff salary in cinemaId={}...", cinemaId);

    Cinema cinema = cinemaRepo.findCinemaById(cinemaId);

    List<Staff> staffList = cinema.getStaffList();

    return staffList.stream()
        .mapToInt(Staff::getSalary)
        .average()
        .getAsDouble();

  }

  public Cinema createNewCinema(String name, Set<Movie> movies, List<Staff> staffList)
      throws EmptyMovieSetException, EmptyStaffListException {

    Cinema newCinema = new Cinema(name, movies, staffList);

    cinemaRepo.save(newCinema);

    if (movies.isEmpty()) {
      throw new EmptyMovieSetException("Set of movies is empty");
    }

    if (staffList.isEmpty()) {
      throw new EmptyStaffListException("List of staff is empty");
    }

    return newCinema;

  }





}