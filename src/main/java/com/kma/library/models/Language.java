package com.kma.library.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = LanguageDeserializer.class)
public enum Language {
  MANDARIN("Mandarin"),
  SPANISH("Spanish"),
  ENGLISH("English"),
  HINDI("Hindi"),
  PORTUGUESE("Portuguese"),
  UNKNOWN("Unknown");

  public String name;

  Language(String n) {
    this.name = n;
  }

  @Override
  public String toString() {
    return name;
  }
}
