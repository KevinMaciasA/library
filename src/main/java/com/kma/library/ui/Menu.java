package com.kma.library.ui;

import java.util.List;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.kma.library.models.Book;
import com.kma.library.models.Language;
import com.kma.library.models.Person;

public class Menu {
  private Scanner scanner = new Scanner(System.in);

  public Menu() {

  }

  public void greet() {
    System.out.println("""
        Welcome to Lib,
        your Book Finder app!""");
  }

  public States main(List<Option> options) {
    options.forEach(System.out::println);
    System.out.println("Navigate through the number:");

    var input = scanner.nextLine();
    try {
      var n = Integer.parseInt(input);
      for (Option option : options) {
        if (option.id() == n)
          return option.state();
      }

      throw new Exception("Invalid option");
    } catch (Exception e) {
      System.out.println("--------------------------------");
      System.out.println("           * Error *           ");
      System.out.println(e.getMessage() + ", " + "please try again...");
      System.out.println("--------------------------------");
      return States.ERROR;
    }
  }

  public String bookTitle() {
    System.out.println("Write down the book title:");
    return scanner.nextLine();
  }

  public void books(List<Book> books) {
    String booksToStr = books
        .stream()
        .map(b -> "* " + b.toString())
        .collect(Collectors.joining("\n"));

    var result = booksToStr.isBlank() ? "None :(" : booksToStr;

    System.out.println("""
        ----------- Books -----------

        %s""".formatted(result));
  }

  public void authors(List<Person> authors) {
    String authorsToStr = authors
        .stream()
        .map(a -> "* " + a.toString())
        .collect(Collectors.joining("\n"));

    var result = authorsToStr.isBlank() ? "None :(" : authorsToStr;

    System.out.println("""
        ----------- Authors -----------

        %s""".formatted(result));
  }

  public Integer getYear() {
    System.out.println("Write down the target year:");
    var input = scanner.nextLine();
    try {
      return Integer.parseInt(input);
    } catch (Exception e) {
      System.out.println("""
          -------------------------------------
          Invalid year format, please try again
          -------------------------------------""");
      return null;
    }
  }

  public void error() {
    System.out.println("------------------------------------");
    System.out.println("  An error has ocurred :(");
    System.out.println("You will be sended to the main menu");
    System.out.println("------------------------------------");
  }

  public void pause() {
    scanner.nextLine();
  }

  public Language languages() {
    var options = new HashMap<Integer, Language>();
    var i = 1;
    for (var lang : Language.values()) {
      if (lang == Language.UNKNOWN)
        continue; // filter Unknown language

      System.out.println("""
          %d. %s""".formatted(i, lang.toString()));
      options.put(i++, lang);
    }

    System.out.println("Select the language by its number:");
    var input = scanner.nextLine();
    try {
      var n = Integer.parseInt(input);
      return options.get(n);
    } catch (Exception e) {
      System.out.println("""
          -------------------------------------
          Invalid option, please try again
          -------------------------------------""");
      return Language.UNKNOWN;
    }
  }

  public void booksByLang(List<Book> books, Language language) {
    String booksToStr = books
        .stream()
        .map(b -> "* " + b.toString())
        .collect(Collectors.joining("\n"));

    var result = booksToStr.isBlank() ? "None :(" : booksToStr;

    System.out.println("""
        ----------- %s Books -----------

        %s
        """.formatted(language.toString(), result));
  }
}
