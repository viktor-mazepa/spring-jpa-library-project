package com.library.solution.controllers;

import com.library.solution.common.Constants;
import com.library.solution.models.Person;
import com.library.solution.services.BookService;
import com.library.solution.services.PeopleService;
import com.library.solution.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String getAllPeople(Model model) {
        model.addAttribute(Constants.PEOPLE_ATTRIBUTE_NAME, peopleService.getAllPeople());
        return "people/people";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.savePerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getPersonInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute(Constants.PERSON_ATTRIBUTE_NAME, peopleService.getPersonById(id));
        model.addAttribute(Constants.BOOKZ_ATTRIBUTE_NAME, peopleService.getBookAssignedToPerson(id));
        return "people/info";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute(Constants.PERSON_ATTRIBUTE_NAME, peopleService.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.updatePerson(id, person);
        return "redirect:/people/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.deletePerson(id);
        return "redirect:/people";
    }
}
