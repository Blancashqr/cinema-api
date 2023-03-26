package com.blancash.cinemaapi.repos;

import com.blancash.cinemaapi.models.Cinema;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepo extends CrudRepository<Cinema, Long> {

  List<Cinema> findAll();

  Cinema findCinemaById(int id);



}
