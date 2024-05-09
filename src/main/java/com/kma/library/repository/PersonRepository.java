package com.kma.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kma.library.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
  @Query("SELECT p FROM Person p WHERE p.birthYear <= :year AND (:year <= p.deathYear OR p.deathYear IS NULL)")
  List<Person> findAuthorsAliveAtAYear(Integer year);
}
