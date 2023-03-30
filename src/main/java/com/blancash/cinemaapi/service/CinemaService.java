package com.blancash.cinemaapi.service;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.repos.CinemaRepo;
import com.blancash.cinemaapi.repos.MovieRepo;
import com.blancash.cinemaapi.repos.StaffRepo;
import com.blancash.cinemaapi.service.exception.EmptyMovieSetException;
import com.blancash.cinemaapi.service.exception.EmptyStaffListException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class CinemaService {

    private final CinemaRepo cinemaRepo;
    private final MovieRepo movieRepo;

    private final StaffRepo staffRepo;

    @Autowired
    public CinemaService(CinemaRepo cinemaRepo, MovieRepo movieRepo, StaffRepo staffRepo) {
        this.cinemaRepo = cinemaRepo;
        this.movieRepo = movieRepo;
        this.staffRepo = staffRepo;
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

    public Cinema createNewCinema(String name, List<List<Integer>> idList)
            throws EmptyMovieSetException, EmptyStaffListException {
        log.info("Creating new cinema called name={}...", name);

        List<Integer> movieIdList = idList.get(0);
        Set<Movie> movieSet = movieRepo.findMovieByIdIn(movieIdList);

        List<Integer> staffIdList = idList.get(1);
        List<Staff> staffList = staffRepo.findStaffByIdIn(staffIdList);

        if (movieSet == null || movieSet.isEmpty()) {
            throw new EmptyMovieSetException("Set of movies is null or empty.");
        } else if (staffList == null || staffList.isEmpty()) {
            throw new EmptyStaffListException("List of staff is null or empty.");
        }

        Cinema newCinema = new Cinema(name, movieSet, staffList);

        cinemaRepo.save(newCinema);

        log.info("Successfully created cinema with name={} and id={}.", name, newCinema.getId());

        return newCinema;

    }


}