package com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.MathGrade;
import org.springframework.data.repository.CrudRepository;

public interface MathGradesDAO extends CrudRepository<MathGrade, Integer> {
    Iterable<MathGrade> findGradeByStudentId(int id);

    void deleteByStudentId(int id);
}
