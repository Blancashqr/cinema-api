package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  private final ClientService clientService;

  @Autowired
  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping("/clients")
  public List<Client> getAllClients() {
    return clientService.getAllClients();
  }

  @GetMapping("/client/{id}")
  public Client getClientById(@PathVariable int id) {
    return clientService.getClientById(id);
  }

  @GetMapping("/client/fav/genre/{id}")
  public Genre getFavGenre(@PathVariable int id) {
    return clientService.getFavGenre(id);
  }

  @GetMapping("/money/spent/{clientId}")
  public int totalMoneySpent(@PathVariable int clientId) {
    return clientService.totalMoneySpent(clientId);
  }

}
