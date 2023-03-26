package com.blancash.cinemaapi.service;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Genre;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Ticket;
import com.blancash.cinemaapi.repos.ClientRepo;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientService {

  private final ClientRepo clientRepo;

  @Autowired
  public ClientService (ClientRepo clientRepo) {
    this.clientRepo = clientRepo;
  }


  public List<Client> getAllClients() {
    return clientRepo.findAll();
  }

  public Client getClientById(int id) {
    return clientRepo.findClientById(id);
  }

  public Genre getFavGenre(int id) {

    Client client = clientRepo.findClientById(id);

    List<Genre> genreList = client.getTickets().stream()
        .map(Ticket::getMovie)
        .map(Movie::getGenre)
        .toList();

    int action = 0;
    int animation = 0;
    int comedy = 0;
    int fantasy = 0;
    int sci_fi = 0;

    for (Genre i : genreList) {
      switch (i) {
        case ACTION -> action+=1;
        case ANIMATION -> animation+=1;
        case COMEDY -> comedy+=1;
        case FANTASY -> fantasy+=1;
        case SCI_FI -> sci_fi+=1;
      }
    }

    Map<Integer, Genre> genreMap = new HashMap<>();
    genreMap.put(action, Genre.ACTION);
    genreMap.put(animation, Genre.ANIMATION);
    genreMap.put(comedy, Genre.COMEDY);
    genreMap.put(fantasy, Genre.FANTASY);
    genreMap.put(sci_fi, Genre.SCI_FI);

    int biggestNumber = genreMap.keySet()
        .stream()
        .mapToInt(Integer::intValue)
        .max().getAsInt();

    return genreMap.get(biggestNumber);

  }

  public int totalMoneySpent(int clientId) {
    log.info("Checking total money spent by clientId={}...", clientId);

    Client client = clientRepo.findClientById(clientId);

    List<Ticket> ticketList = client.getTickets();

    return ticketList.stream()
        .mapToInt(Ticket::getPrice)
        .sum();

  }
}


