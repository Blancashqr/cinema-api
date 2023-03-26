package com.blancash.cinemaapi.repos;

import com.blancash.cinemaapi.models.Ticket;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends CrudRepository<Ticket, Long> {

  List<Ticket> findAll();

  Ticket findTicketById(int id);
}
