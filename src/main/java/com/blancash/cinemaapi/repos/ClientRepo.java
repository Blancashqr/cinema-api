package com.blancash.cinemaapi.repos;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Genre;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends CrudRepository<Client, Long> {

  List<Client> findAll();

  Client findClientById(int id);



}
