package com.kma.library.ui;

public enum States {
  INIT(1),
  MAIN(2),
  TITLE(3),
  BOOKS(4),
  AUTHORS(5),
  TIMELINE(6),
  LANGUAGE(7),
  EXIT(0),
  ERROR(-1);

  public Integer n;

  States(Integer n) {
    this.n = n;
  }

  public static States fromInt(Integer n) {
    for (var state : States.values()) {
      if (state.n == n)
        return state;
    }
    return States.ERROR;
  }
}
