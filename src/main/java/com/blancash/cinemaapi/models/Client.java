package com.blancash.cinemaapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Client {

  @Id
  private int id;

  private String name;

  private int age;

  private String email;

  @OneToMany(mappedBy = "client")
      @JsonIgnoreProperties(value = "client")
  List<Ticket> tickets;

}
