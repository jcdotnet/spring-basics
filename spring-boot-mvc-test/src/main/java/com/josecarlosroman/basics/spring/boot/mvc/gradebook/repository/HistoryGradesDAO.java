package com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.HistoryGrade;
import org.springframework.data.repository.CrudRepository;

public interface HistoryGradesDAO extends CrudRepository<HistoryGrade, Integer> {
    Iterable<HistoryGrade> findGradeByStudentId(int id);

    void deleteByStudentId(int id);
}
