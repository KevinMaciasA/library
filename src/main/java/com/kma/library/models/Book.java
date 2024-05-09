package com.kma.library.models;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
  @JsonProperty("id")
  @Id
  private int id;
  @JsonProperty("title")
  private String title;
  @JsonProperty("subjects")
  @ElementCollection
  private List<String> subjects;
  @JsonProperty("authors")
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Person> authors;
  @JsonProperty("languages")
  @Enumerated(value = EnumType.STRING)
  private List<Language> languages;
  @JsonProperty("download_count")
  private Integer downloadCount;

  public Integer id() {
    return id;
  }

  public String title() {
    return title;
  }

  public List<String> subjects() {
    return subjects;
  }

  public List<Person> authors() {
    return authors;
  }

  public List<Language> languages() {
    return languages;
  }

  public int downloads() {
    return downloadCount;
  }

  @Override
  public String toString() {
    String authorsStr = authors
        .stream()
        .map(a -> a.toString())
        .collect(Collectors.joining(", "));

    return """
        %s | %s (%d)""".formatted(title, authorsStr, downloadCount);
  }

}
