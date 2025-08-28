package com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.ScienceGrade;
import org.springframework.data.repository.CrudRepository;

public interface ScienceGradesDAO extends CrudRepository<ScienceGrade, Integer> {
    Iterable<ScienceGrade> findGradeByStudentId(int id);

    void deleteByStudentId(int id);
}
