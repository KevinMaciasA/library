package com.kma.library.ui;

public record Option(Integer id, States state, String description) {

  @Override
  public final String toString() {
    return """
        %d. %s""".formatted(id, description);
  }
}
