package com.blancash.cinemaapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Ticket;
import com.blancash.cinemaapi.repos.ClientRepo;
import com.blancash.cinemaapi.repos.MovieRepo;
import com.blancash.cinemaapi.repos.TicketRepo;
import com.blancash.cinemaapi.service.exception.MovieNotReleasedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TicketServiceTest {

  @Mock
  TicketRepo ticketRepo;

  @Mock
  ClientRepo clientRepo;

  @Mock
  MovieRepo movieRepo;

  TicketService ticketService;


  @BeforeEach
  void setup() {
    ticketRepo = mock(TicketRepo.class);
    clientRepo = mock(ClientRepo.class);
    movieRepo = mock(MovieRepo.class);
    ticketService = new TicketService(ticketRepo, clientRepo, movieRepo);
  }

  @Test
  void checkGetAllTickets() {

    Ticket ticket = new Ticket(1, 5, 23, new Movie(), new Client());
    List<Ticket> ticketList = List.of(ticket);

    doReturn(ticketList).when(ticketRepo).findAll();

    ticketService.getAllTickets();

    verify(ticketRepo).findAll();

  }

  @Test
  void checkGetTicketById() {

    int id = 1;
    Ticket ticket = new Ticket(1, 5, 23, new Movie(), new Client());

    doReturn(ticket).when(ticketRepo).findTicketById(id);

    Ticket result = ticketService.getTicketById(id);

    assertEquals(1, result.getId());

    verify(ticketRepo).findTicketById(id);

  }

  @Test
  void checkBuyNewTicketMovieReleased() throws MovieNotReleasedException {

    int clientId = 1;
    Client client = new Client(1, "Blanca", 25, "", new ArrayList<>());
    int movieId = 2;
    Movie movie = new Movie(2, "Avatar", "2023-23-01", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        new ArrayList<>());
    Ticket ticket = new Ticket(1, 5, 23, movie, client);

    doReturn(client).when(clientRepo).findClientById(anyInt());
    doReturn(movie).when(movieRepo).findMovieById(anyInt());
    doReturn(ticket).when(ticketRepo).save(any());
    doReturn(client).when(clientRepo).save(any());
    doReturn(movie).when(movieRepo).save(any());

    Ticket result = ticketService.buyNewTicket(clientId, movieId);

    assertEquals(1, client.getTickets().get(0).getId());
    assertEquals(1, movie.getTickets().get(0).getId());

  }

  @Test
  void checkBuyNewTicketMovieNotReleased() {

    int clientId = 1;
    Client client = new Client(1, "Blanca", 25, "", new ArrayList<>());
    int movieId = 2;
    Movie movie = new Movie(2, "Avatar", "3000-23-01", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        new ArrayList<>());

    doReturn(client).when(clientRepo).findClientById(anyInt());
    doReturn(movie).when(movieRepo).findMovieById(anyInt());

    MovieNotReleasedException thrown = Assertions.assertThrows(MovieNotReleasedException.class, () -> ticketService.buyNewTicket(clientId, movieId));

    assertEquals("Movie not released yet.", thrown.getMessage());

  }

}
