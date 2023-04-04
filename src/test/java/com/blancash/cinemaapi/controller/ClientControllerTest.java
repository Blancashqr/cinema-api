package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

        doReturn(client).when(clientService).getClientById(anyInt());

        clientController.getClientById(id);

        verify(clientService).getClientById(id);

    }

    @Test
    void checkGetFavGenre() {

        int id = 1;

        doReturn(Genre.FANTASY).when(clientService).getFavGenre(anyInt());

        clientController.getFavGenre(id);

        verify(clientService).getFavGenre(id);

    }

    @Test
    void checkGetTotalMoneySpent() {

        int id = 1;
        int result = 300;

        doReturn(result).when(clientService).totalMoneySpent(anyInt());

        clientController.totalMoneySpent(id);

        verify(clientService).totalMoneySpent(id);

    }


}
