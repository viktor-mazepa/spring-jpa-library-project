package com.library.solution.models;

import com.library.solution.common.Constants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = Constants.TITLE_SHOULDNT_BE_EMPTY_ERROR_MSG)
    @Size(min = 1, max = 200, message = Constants.TITLE_SIZE_ERROR_MSG)
    private String title;

    @Column(name = "author")
    @NotEmpty(message = Constants.AUTHOR_EMPTY_ERROR_MSG)
    @Size(min = 1, max = 200, message = Constants.AUTHOR_SIZE_ERROR_MSG)
    private String author;

    @Column(name = "year_of_publication")
    @NotNull(message = Constants.YEAR_EMPTY_ERROR_MSG)
    @Min(value = 1, message = Constants.YEAR_LESS_ERROR_MSG)
    private int yearOfPublication;

    @Column(name = "reservation_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date reservationDate;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Transient
    private Boolean overdue;

    public Book() {
    }

    public Book(int id, String title, String author, int yearOfPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && yearOfPublication == book.yearOfPublication && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(reservationDate, book.reservationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, yearOfPublication, reservationDate);
    }
}
