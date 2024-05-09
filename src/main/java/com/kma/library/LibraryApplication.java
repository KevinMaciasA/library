package com.kma.library;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kma.library.models.Language;
import com.kma.library.repository.BookRepository;
import com.kma.library.repository.PersonRepository;
import com.kma.library.services.BooksAPI;
import com.kma.library.ui.Menu;
import com.kma.library.ui.Option;
import com.kma.library.ui.States;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private PersonRepository personRepo;
	private Menu menu = new Menu();
	private States current = States.INIT;
	private BooksAPI api = new BooksAPI();

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while (current != States.EXIT) {
			switch (current) {
				case INIT:
					initState();
					break;
				case MAIN:
					mainState();
					break;
				case TITLE:
					titleState();
					break;
				case BOOKS:
					booksState();
					break;
				case AUTHORS:
					authorsState();
					break;
				case TIMELINE:
					timelineState();
					break;
				case LANGUAGE:
					languageState();
					break;
				case ERROR:
					errorState();
					break;
				default:
					break;
			}

		}
	}

	public void initState() {
		menu.greet();
		current = States.MAIN;
	}

	public void mainState() {
		var options = Arrays.asList(
				new Option(1, States.TITLE, "Find Book by Title"),
				new Option(2, States.BOOKS, "Show all Books"),
				new Option(3, States.AUTHORS, "List all Authors"),
				new Option(4, States.TIMELINE, "Show Authors alive at specific Year"),
				new Option(5, States.LANGUAGE, "Show Books by their Language"),
				new Option(0, States.EXIT, "Exit"));
		var next = menu.main(options);

		if (next != States.ERROR)
			current = next;
	}

	private void titleState() {
		var title = menu.bookTitle();
		try {
			var maybeBook = api.searchBook(title);
			maybeBook.ifPresentOrElse(b -> {
				bookRepo.save(b);
				System.out.println("-------------------------------------");
				System.out.println("* " + b.toString());
				System.out.println("-------------------------------------");
			}, // else
					() -> {
						System.out.println("-------------------------------------");
						System.out.println(" Sorry, we couldn't find the book :(");
						System.out.println("-------------------------------------");
					});

			current = States.MAIN;
		} catch (Exception e) {
			e.printStackTrace();
			current = States.ERROR;
		}
	}

	private void booksState() {
		var books = bookRepo.findAll();
		menu.books(books);
		menu.pause();
		current = States.MAIN;

	}

	private void authorsState() {
		var authors = personRepo.findAll();
		menu.authors(authors);
		menu.pause();
		current = States.MAIN;
	}

	private void timelineState() {
		var year = menu.getYear();

		if (year == null)
			return;

		var authors = personRepo.findAuthorsAliveAtAYear(year);
		menu.authors(authors);
		menu.pause();
		current = States.MAIN;
	}

	private void languageState() {
		var lang = menu.languages();

		if (lang == Language.UNKNOWN)
			return;

		var books = bookRepo.findAll();
		var targetBooks = books
				.stream()
				.filter(b -> b.languages().contains(lang))
				.toList();
		menu.booksByLang(targetBooks, lang);
		menu.pause();
		current = States.MAIN;
	}

	private void errorState() {
		menu.error();
		current = States.MAIN;
	}
}
