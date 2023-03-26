package com.blancash.cinemaapi.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Movie {

  @Id
  private int id;

  private String name;

  private String releaseDate;

  private Genre genre;

  private boolean tresde;

  private String rating;

  private int duration;

  @ManyToMany(mappedBy = "movies")
  @JsonIgnoreProperties(value = "movies")
  private Set<Cinema> cinemas;

  @OneToMany(mappedBy = "movie")
  @JsonIgnoreProperties(value = "movie")
  private List<Ticket> tickets;

}
