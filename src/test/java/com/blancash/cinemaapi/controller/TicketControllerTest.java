package com.blancash.cinemaapi.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Ticket;
import com.blancash.cinemaapi.service.TicketService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TicketControllerTest {

  @Mock
  TicketService ticketService;

  TicketController ticketController;

  @BeforeEach
  void setup() {
    ticketService = mock(TicketService.class);
    ticketController = new TicketController(ticketService);
  }

  @Test
  void checkGetAllTickets() {

    Ticket ticket = new Ticket(1, 5, 23, new Movie(), new Client());
    List<Ticket> ticketList = List.of(ticket);

    doReturn(ticketList).when(ticketService).getAllTickets();

    ticketController.getAllTickets();

    verify(ticketService).getAllTickets();

  }

  @Test
  void checkGetTicketById() {

    int id = 1;
    Ticket ticket = new Ticket(1, 5, 23, new Movie(), new Client());

    doReturn(ticket).when(ticketService).getTicketById(id);

    ticketController.getTicketById(id);

    verify(ticketService).getTicketById(id);

  }

}
