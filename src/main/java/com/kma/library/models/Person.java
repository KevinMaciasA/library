package com.kma.library.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonProperty("birth_year")
  private Integer birthYear;
  @JsonProperty("death_year")
  private Integer deathYear;
  @JsonProperty("name")
  private String name;
  @ManyToMany(mappedBy = "authors")
  private List<Book> books;

  public Integer birthYear() {
    return birthYear;
  }

  public Integer deathYear() {
    return deathYear;
  }

  public String name() {
    return name;
  }

  public List<Book> books() {
    return books;
  }

  public void id(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return name;
  }
}
