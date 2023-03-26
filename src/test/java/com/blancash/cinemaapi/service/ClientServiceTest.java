package com.blancash.cinemaapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Ticket;
import com.blancash.cinemaapi.repos.ClientRepo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class ClientServiceTest {

  @Mock
  ClientRepo clientRepo;

  ClientService clientService;

  @BeforeEach
  void setup() {
    clientRepo = mock(ClientRepo.class);
    clientService = new ClientService(clientRepo);
  }

  @Test
  void checkGetAllClients() {

    Client client = new Client(1, "Blanca", 25, "", new ArrayList<>());
    List<Client> clientList = List.of(client);

    doReturn(clientList).when(clientRepo).findAll();

    clientService.getAllClients();

    verify(clientRepo).findAll();

  }

  @Test
  void checkGetClientById() {

    int id = 1;
    Client client = new Client(1, "Blanca", 25, "", new ArrayList<>());

    doReturn(client).when(clientRepo).findClientById(anyInt());

    clientService.getClientById(id);

    verify(clientRepo).findClientById(id);

  }

  @Test
  void checkGetFavGenre() {

    Movie movie1 = new Movie(1, "Avatar", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        new ArrayList<>());
    Movie movie2 = new Movie(2, "Narnia", "02-01-23", Genre.FANTASY, true, "PG-13", 180, new HashSet<>(),
        new ArrayList<>());
    Movie movie3 = new Movie(3, "Titanic", "02-01-23", Genre.COMEDY, true, "PG-13", 180, new HashSet<>(),
        new ArrayList<>());

    Ticket ticket1 = new Ticket(1, 5, 23, movie1, new Client());
    Ticket ticket2 = new Ticket(2, 5, 23, movie2, new Client());
    Ticket ticket3 = new Ticket(3, 5, 23, movie3, new Client());
    List<Ticket> ticketList = List.of(ticket1, ticket2, ticket3);

    int id = 1;
    Client client = new Client(1, "Blanca", 25, "", ticketList);

    doReturn(client).when(clientRepo).findClientById(anyInt());

    Genre result = clientService.getFavGenre(id);

    assertEquals(Genre.FANTASY, result);

    verify(clientRepo).findClientById(id);

  }

  @Test
  void checkTotalMoneySpent() {

    int id = 1;
    Ticket ticket1 = new Ticket(5, 34, new Movie(), new Client());
    Ticket ticket2 = new Ticket(10, 45, new Movie(), new Client());
    List<Ticket> ticketList = List.of(ticket1, ticket2);
    Client client = new Client(id, "Lola", 27, "lola@gmail.com", ticketList);

    doReturn(client).when(clientRepo).findClientById(anyInt());

    int result = clientService.totalMoneySpent(id);

    assertEquals(15, result);

    verify(clientRepo).findClientById(id);

  }


}
