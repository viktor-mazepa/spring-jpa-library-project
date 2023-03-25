package com.library.solution.services;

import com.library.solution.models.Book;
import com.library.solution.models.Person;
import com.library.solution.repositories.BookRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private BookRepository bookRepository;

    private PeopleService personService;

    @Autowired
    public BookService(BookRepository bookRepository, PeopleService personService) {
        this.bookRepository = bookRepository;
        this.personService = personService;
    }


    public Collection<Book> getAllBooks(Integer page, Integer booksPerPage, Boolean sortByYear) {
        if (sortByYear == Boolean.FALSE) {
            if (page != null && booksPerPage != null) {
                return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
            } else {
                return bookRepository.findAll();
            }
        } else {
            if (page != null && booksPerPage != null) {
                return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfPublication"))).getContent();
            } else {
                return bookRepository.findAll(Sort.by("yearOfPublication"));
            }
        }
    }

    public Collection<Book> getAllBooks(Integer page, Integer booksPerPage) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    @Transactional(readOnly = false)
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBookByPartOfName(String partOfName)
    {
        Optional<Book> bookOptional = bookRepository.findByTitleStartingWith(partOfName);
        return bookOptional.orElse(null);
    }

    public Book getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional(readOnly = false)
    public void updateBook(int id, Book book) {
        Book bookFromDB = getBookById(id);
        book.setId(bookFromDB.getId());
        book.setOwner(bookFromDB.getOwner());
        book.setReservationDate(bookFromDB.getReservationDate());
        bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public void releaseBook(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Person person = book.get().getOwner();
            Hibernate.initialize(person.getBooks());
            person.getBooks().remove(book);
            book.get().setOwner(null);
            book.get().setReservationDate(null);
        }
    }

    @Transactional(readOnly = false)
    public void assignBook(int bookId, int personId) {
        Person person = personService.getPersonById(personId);
        Book book = getBookById(bookId);
        if (person != null && book != null) {
            book.setOwner(person);
            person.addBook(book);
            book.setReservationDate(new Date());
        }
    }
}
