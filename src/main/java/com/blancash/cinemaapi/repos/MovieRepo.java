package com.blancash.cinemaapi.repos;

import com.blancash.cinemaapi.models.Movie;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends CrudRepository<Movie, Long> {

  List<Movie> findAll();

  Movie findMovieById(int id);

  Movie findMovieByName(String name);

}
