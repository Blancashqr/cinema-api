package com.blancash.cinemaapi;

import com.blancash.cinemaapi.repos.CinemaRepo;
import com.blancash.cinemaapi.repos.ClientRepo;
import com.blancash.cinemaapi.repos.MovieRepo;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {

  @Autowired
  private CinemaRepo cinemaRepo;

  @Autowired
  private MovieRepo movieRepo;

  @Autowired
  private ClientRepo clientRepo;

  @Transactional
  @Override
  public void run(String... args) {
    log.info("Cinemas:");
    cinemaRepo.findAll()
        .forEach(c -> log.info(c.toString()));

    log.info("Movies:");
    movieRepo.findAll()
        .forEach(o -> log.info(o.toString()));

    log.info("Clients:");
    clientRepo.findAll()
        .forEach(p -> log.info(p.toString()));
  }

}
