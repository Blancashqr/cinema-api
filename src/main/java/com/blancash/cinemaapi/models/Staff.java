package com.blancash.cinemaapi.models;

import com.blancash.cinemaapi.repos.CinemaRepo;
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
public class Staff {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private int salary;

  @ManyToOne
  @JsonIgnoreProperties(value = "staffList")
  private Cinema cinema;

  public Staff(String name, int salary, Cinema cinema) {
    this.name = name;
    this.salary = salary;
    this.cinema = cinema;
  }

}
