package com.library.solution.models;

import com.library.solution.common.Constants;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "fullname")
    @NotEmpty(message = Constants.NAME_EMPTY_ERROR_MSG)
    @Size(min = 2, max = 200, message = Constants.NAME_SIZE_ERROR_MSG)
    private String fullName;

    @Column(name = "year_birth")
    @NotNull(message = Constants.YEAR_BIRTH_EMPTY_ERROR_MSG)
    @Min(value = 1901, message = Constants.YEAR_BIRTH_LESS_ERROR_MSG)
    private int yearBirth;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToMany(mappedBy = "owner")
    private Collection<Book> books;

    public Person() {
    }

    public Person(int id, String fullName, int yearBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearBirth = yearBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
        }
        this.books.add(book);
        book.setOwner(this);
    }
}
