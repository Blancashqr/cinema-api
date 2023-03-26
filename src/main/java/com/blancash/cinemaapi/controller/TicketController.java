package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Ticket;
import com.blancash.cinemaapi.service.TicketService;
import com.blancash.cinemaapi.service.exception.MovieNotReleasedException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TicketController {

  private final TicketService ticketService;

  @Autowired
  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @GetMapping("/tickets")
  public List<Ticket> getAllTickets() {
    return ticketService.getAllTickets();
  }

  @GetMapping("/ticket/{id}")
  public Ticket getTicketById(@PathVariable int id) {
    return ticketService.getTicketById(id);
  }

  @PostMapping("/ticket/buy/{clientId}/{movieId}")
  public Ticket buyNewTicket(@PathVariable int clientId, @PathVariable int movieId) {
    try {
      return ticketService.buyNewTicket(clientId, movieId);
    }
    catch (MovieNotReleasedException e) {
      log.error(e.getMessage());
      return new Ticket();
    }
  }


}
