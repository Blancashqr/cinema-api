package com.blancash.cinemaapi.service;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Ticket;
import com.blancash.cinemaapi.repos.ClientRepo;
import com.blancash.cinemaapi.repos.MovieRepo;
import com.blancash.cinemaapi.repos.TicketRepo;
import com.blancash.cinemaapi.service.exception.MovieNotReleasedException;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TicketService {

  private final TicketRepo ticketRepo;
  private final ClientRepo clientRepo;
  private final MovieRepo movieRepo;

  @Autowired
  public TicketService(TicketRepo ticketRepo, ClientRepo clientRepo, MovieRepo movieRepo) {
    this.ticketRepo = ticketRepo;
    this.clientRepo = clientRepo;
    this.movieRepo = movieRepo;
  }

  public List<Ticket> getAllTickets() {
    return ticketRepo.findAll();
  }

  public Ticket getTicketById(int id) {
    return ticketRepo.findTicketById(id);
  }

  public Ticket buyNewTicket(int clientId, int movieId) throws MovieNotReleasedException {
    log.info("Buying ticket for clientId={} and movieId={}...", clientId, movieId);

    Client client = clientRepo.findClientById(clientId);
    Movie movie = movieRepo.findMovieById(movieId);

    Ticket ticket = new Ticket(5, 34, movie, client);

    LocalDate releaseDate = movieReleaseDate(movie.getReleaseDate());

    if (releaseDate.isAfter(LocalDate.now())) {
      throw new MovieNotReleasedException("Movie not released yet.");
    }

    Ticket newTicket = ticketRepo.save(ticket);

    List<Ticket> clientTickets = client.getTickets();
    clientTickets.add(newTicket);
    clientRepo.save(client);

    List<Ticket> movieTickets = movie.getTickets();
    movieTickets.add(newTicket);
    movieRepo.save(movie);
    log.info("Successfully bought ticket for clientId={} and movieId={}.", clientId, movieId);

    return newTicket;

  }

  private LocalDate movieReleaseDate(String releaseDate) {

    String[] releaseDateArray = releaseDate.split("-");

    int year = Integer.parseInt(releaseDateArray[0]);
    int day = Integer.parseInt(releaseDateArray[1]);
    int month = Integer.parseInt(releaseDateArray[2]);

    return LocalDate.of(year, month, day);

  }
}
