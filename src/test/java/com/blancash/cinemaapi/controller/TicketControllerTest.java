package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Client;
import com.blancash.cinemaapi.models.Movie;
import com.blancash.cinemaapi.models.Ticket;
import com.blancash.cinemaapi.service.TicketService;
import com.blancash.cinemaapi.service.exception.MovieNotReleasedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

    @Test
    void checkBuyNewTicket() throws MovieNotReleasedException {

        int clientId = 1;
        int movieId = 2;
        Ticket ticket = new Ticket();

        doReturn(ticket).when(ticketService).buyNewTicket(anyInt(), anyInt());

        ticketController.buyNewTicket(clientId, movieId);

        verify(ticketService).buyNewTicket(clientId, movieId);

    }

    @Test
    void checkBuyNewTicketMovieNotReleased() throws MovieNotReleasedException {

        int clientId = 1;
        int movieId = 1;

        doThrow(new MovieNotReleasedException("Movie not release yet."))
                .when(ticketService).buyNewTicket(anyInt(), anyInt());

        Ticket result = ticketController.buyNewTicket(clientId, movieId);

        assertEquals(0, result.getId());

        verify(ticketService).buyNewTicket(clientId, movieId);

    }

}
