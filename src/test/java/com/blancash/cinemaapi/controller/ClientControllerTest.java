package com.blancash.cinemaapi.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.service.ClientService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class ClientControllerTest {

  @Mock
  ClientService clientService;

  ClientController clientController;

  @BeforeEach
  void setup() {
    clientService = mock(ClientService.class);
    clientController = new ClientController(clientService);
  }

  @Test
  void checkGetAllClients() {

    Client client = new Client(1, "Blanca", 25, "", new ArrayList<>());
    List<Client> clientList = List.of(client);

    doReturn(clientList).when(clientService).getAllClients();

    clientController.getAllClients();

    verify(clientService).getAllClients();

  }

  @Test
  void checkGetClientById() {

    int id = 1;
    Client client = new Client(1, "Blanca", 25, "", new ArrayList<>());

    doReturn(client).when(clientService).getClientById(id);

    clientController.getClientById(id);

    verify(clientService).getClientById(id);

  }




}
