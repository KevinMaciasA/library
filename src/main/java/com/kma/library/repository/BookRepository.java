package com.kma.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kma.library.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
