package com.library.solution.services;

import com.library.solution.models.Book;
import com.library.solution.models.Person;
import com.library.solution.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Collection<Person> getAllPeople() {
        return peopleRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void savePerson(Person person) {
        peopleRepository.save(person);
    }

    public Optional<Person> getAnyPersonByName(String fullName) {
        return peopleRepository.findByFullName(fullName).stream().findAny();
    }

    public Person getPersonById(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional(readOnly = false)
    public void updatePerson(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional(readOnly = false)
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }

    public Collection<Book> getBookAssignedToPerson(int personId) {
        Optional<Person> optionalPerson = peopleRepository.findById(personId);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            Hibernate.initialize(person.getBooks());
            Collection<Book> books = person.getBooks();
            checkBookOnOverdue(books);
            return books;
        }
        return null;
    }

    private void checkBookOnOverdue(Collection<Book> books) {
        if (books == null)
            return;
        books.forEach(book -> {
            if (getDateDiff(book.getReservationDate(), new Date(), TimeUnit.DAYS) > 10) {
                book.setOverdue(Boolean.TRUE);
            }
        });
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

}
