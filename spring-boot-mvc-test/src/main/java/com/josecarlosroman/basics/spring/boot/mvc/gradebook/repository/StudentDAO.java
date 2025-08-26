package com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.HogwartsStudent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Spring Data JPA
@Repository
public interface StudentDAO extends CrudRepository<HogwartsStudent, Integer> { // <Entity, PrimaryKey>

    public HogwartsStudent findByEmailAddress(String emailAddress);
}
