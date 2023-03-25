package com.library.solution.repositories;

import com.library.solution.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitleStartingWith(String partOfName);
}
