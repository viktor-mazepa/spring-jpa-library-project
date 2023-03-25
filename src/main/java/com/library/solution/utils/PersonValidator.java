package com.library.solution.utils;


import com.library.solution.common.Constants;
import com.library.solution.models.Person;
import com.library.solution.services.PeopleService;
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService ;

    private static final String rejectKey = "fullName";

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (peopleService.getAnyPersonByName(person.getFullName()).isPresent()) {
            errors.rejectValue(rejectKey, "", Constants.USER_ALREADY_EXISTS_ERROR_MSG);
        }
    }
}
