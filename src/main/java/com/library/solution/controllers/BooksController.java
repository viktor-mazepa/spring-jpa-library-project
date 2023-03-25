package com.library.solution.controllers;

import com.library.solution.common.Constants;
import com.library.solution.models.Book;
import com.library.solution.models.Person;
import com.library.solution.services.BookService;
import com.library.solution.services.PeopleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final Logger LOGGER = Logger.getLogger(BooksController.class);

    private final BookService bookService;
    private final PeopleService personService;

    @Autowired
    public BooksController(BookService bookService, PeopleService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping
    public String getAllBooks(Model model,
                              @RequestParam(required = false, name = "page") Integer page,
                              @RequestParam(required = false, name = "books_per_page") Integer booksPerPage,
                              @RequestParam(required = false, name = "sort_by_year", defaultValue = "false") Boolean sortByYear) {
        model.addAttribute(Constants.BOOKZ_ATTRIBUTE_NAME, bookService.getAllBooks(page, booksPerPage, sortByYear));
        if (page != null) {
            model.addAttribute(Constants.PAGE_ATTRIBUTE_NAME, page.intValue());
        }
        return "books/books";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam(required = false, name = "name_for_search") String nameForSearch, Model model) {
        if (StringUtils.isNoneEmpty(nameForSearch)) {
            Book book = bookService.getBookByPartOfName(nameForSearch);
            if (book != null) {
                model.addAttribute(Constants.BOOK_ATTRIBUTE_NAME, book);
                model.addAttribute(Constants.ASSIGNED_PERSON_ATTRIBUTE_NAME, book.getOwner());
            }
            model.addAttribute(Constants.SEARCH_PERFORMED_ATTRIBUTE_NAME, Boolean.TRUE);
        }
        return "books/search";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookService.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBookInfo(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Book book = bookService.getBookById(id);
        model.addAttribute(Constants.BOOK_ATTRIBUTE_NAME, book);
        if (book.getOwner() != null) {
            model.addAttribute(Constants.ASSIGNED_PERSON_ATTRIBUTE_NAME, book.getOwner());
        } else {
            model.addAttribute(Constants.PEOPLE_ATTRIBUTE_NAME, personService.getAllPeople());
        }
        return "books/info";
    }

    @PatchMapping("/{id}/release")
    public String releaseBook(@PathVariable("id") int id) {
        bookService.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String releaseBook(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookService.assignBook(id, selectedPerson.getId());
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute(Constants.BOOK_ATTRIBUTE_NAME, bookService.getBookById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.updateBook(id, book);
        return "redirect:/books/" + id;
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }


}
