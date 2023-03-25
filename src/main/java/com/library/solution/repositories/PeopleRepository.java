package com.library.solution.repositories;

import com.library.solution.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Collection<Person> findByFullName (String fullName);

}
