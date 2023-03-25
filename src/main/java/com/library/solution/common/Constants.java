package com.library.solution.common;

public interface Constants {

    String BOOK_ATTRIBUTE_NAME="book";
    String PAGE_ATTRIBUTE_NAME="page";
    String BOOKZ_ATTRIBUTE_NAME="books";
    String PEOPLE_ATTRIBUTE_NAME="people";
    String PERSON_ATTRIBUTE_NAME="person";
    String ASSIGNED_PERSON_ATTRIBUTE_NAME="assignedPerson";
    String SEARCH_PERFORMED_ATTRIBUTE_NAME="search_performed";


    String TITLE_SHOULDNT_BE_EMPTY_ERROR_MSG = "Название не может быть пустым";
    String TITLE_SIZE_ERROR_MSG="Название книги должно быть не меньше 1 симовола и не больше 200";
    String AUTHOR_EMPTY_ERROR_MSG="ФИО атора не может быть пустым";
    String AUTHOR_SIZE_ERROR_MSG="ФИО автора книги должно быть не меньше 1 симовола и не больше 200";
    String YEAR_EMPTY_ERROR_MSG="Год публикации не может быть пустым";
    String YEAR_LESS_ERROR_MSG="Год публикации должен быть больше 0. Это не музей!";

    String NAME_EMPTY_ERROR_MSG="ФИО читателя не может быть пустым";
    String NAME_SIZE_ERROR_MSG="ФИО должно быть не меньше 10 симоволов и не больше 200";
    String YEAR_BIRTH_EMPTY_ERROR_MSG ="Год рождения не может быть пустым";
    String YEAR_BIRTH_LESS_ERROR_MSG="Год рождения должен быть больше чем 1900";

    String USER_ALREADY_EXISTS_ERROR_MSG="Пользователь с таким ФИО уже существует";

}
