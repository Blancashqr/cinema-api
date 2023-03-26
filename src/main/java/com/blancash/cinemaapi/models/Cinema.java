package com.blancash.cinemaapi.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
public class Cinema {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @ManyToMany
  @JoinTable(
      name = "cinema_movie",
      joinColumns = @JoinColumn(name = "cinema_id"),
      inverseJoinColumns = @JoinColumn(name = "movie_id"))
  @JsonIgnoreProperties(value = "cinemas")
  private Set<Movie> movies;

  @OneToMany(mappedBy = "cinema")
  @JsonIgnoreProperties(value = "cinema")
  private List<Staff> staffList;

  public Cinema(String name, Set<Movie> movies, List<Staff> staffList) {
    this.name = name;
    this.movies = movies;
    this.staffList = staffList;
  }

}
