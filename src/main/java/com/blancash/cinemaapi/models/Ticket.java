package com.blancash.cinemaapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int price;

  private int seats;

  @ManyToOne
  @JsonIgnoreProperties(value = "tickets")
  private Movie movie;

  @ManyToOne
  @JsonIgnoreProperties(value = "tickets")
  private Client client;

  public Ticket(int price, int seats, Movie movie, Client client) {
    this.price = price;
    this.seats = seats;
    this.client = client;
    this.movie = movie;
  }


}
