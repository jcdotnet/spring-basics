package com.josecarlosroman.basics.spring.boot.mvc.gradebook.services;

import com.josecarlosroman.basics.spring.boot.mvc.gradebook.models.*;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.HistoryGradesDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.MathGradesDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.ScienceGradesDAO;
import com.josecarlosroman.basics.spring.boot.mvc.gradebook.repository.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    @Qualifier("mathGrades")
    private MathGrade mathGrade;

    @Autowired
    private MathGradesDAO mathGradesDAO;

    @Autowired
    @Qualifier("scienceGrades")
    private ScienceGrade scienceGrade;

    @Autowired
    private ScienceGradesDAO scienceGradesDAO;

    @Autowired
    @Qualifier("historyGrades")
    private HistoryGrade historyGrade;

    @Autowired
    private HistoryGradesDAO historyGradesDAO;

    public void createStudent(String firstName, String lastName, String email) {
        HogwartsStudent student = new HogwartsStudent(firstName, lastName, email);
        student.setId(0);
        studentDAO.save(student);
    }

    @Autowired
    private StudentGrades studentGrades;

    public Iterable<HogwartsStudent> getGradebook() {
        return studentDAO.findAll();
    }

    public boolean checkIfStudentExists(int id) {
        Optional<HogwartsStudent> student = studentDAO.findById(id);
        return student.isPresent();
    }

    public void deleteStudent(int id) {
        if (checkIfStudentExists(id)) {
            studentDAO.deleteById(id);
            mathGradesDAO.deleteByStudentId(id);
            scienceGradesDAO.deleteByStudentId(id);
            historyGradesDAO.deleteByStudentId(id);
        }
    }

    public boolean createGrade(double grade, int studentId, String subject) {
        if (!checkIfStudentExists(studentId)) return false;
        if (grade < 0 || grade >100) return false;

        if (subject.equals("math")) {
            mathGrade.setId(0);
            mathGrade.setGrade(grade);
            mathGrade.setStudentId(studentId);
            mathGradesDAO.save(mathGrade);
            return true;
        } else if (subject.equals("science")) {
            scienceGrade.setId(0);
            scienceGrade.setGrade(grade);
            scienceGrade.setStudentId(studentId);
            scienceGradesDAO.save(scienceGrade);
            return true;
        } else if (subject.equals("history")) {
            historyGrade.setId(0);
            historyGrade.setGrade(grade);
            historyGrade.setStudentId(studentId);
            historyGradesDAO.save(historyGrade);
            return true;
        }
        return false;
    }

    public int deleteGrade(int gradeId, String subject) {
        int studentId = 0;
        if (subject.equals("math")) {
            Optional<MathGrade> grade = mathGradesDAO.findById(gradeId);
            if (grade.isEmpty()) return studentId;
            else {
                mathGradesDAO.deleteById(gradeId);
                return grade.get().getStudentId();
            }
        } else if (subject.equals("science")) {
            Optional<ScienceGrade> grade = scienceGradesDAO.findById(gradeId);
            if (grade.isEmpty()) return studentId;
            else {
                scienceGradesDAO.deleteById(gradeId);
                return grade.get().getStudentId();
            }
        } else if (subject.equals("history")) {
            Optional<HistoryGrade> grade = historyGradesDAO.findById(gradeId);
            if (grade.isEmpty()) return studentId;
            else {
                historyGradesDAO.deleteById(gradeId);
                return grade.get().getStudentId();
            }
        }
        return studentId;
    }

    public GradebookStudent studentInformation(int id) {
        Optional<HogwartsStudent> student = studentDAO.findById(id);

        if (student.isEmpty()) return null;

        Iterable<MathGrade> mathGrades = mathGradesDAO.findGradeByStudentId(id);
        Iterable<ScienceGrade> scienceGrades = scienceGradesDAO.findGradeByStudentId(id);
        Iterable<HistoryGrade> historyGrades = historyGradesDAO.findGradeByStudentId(id);

        // converting iterables to list
        List<Grade> mathGradesList = new ArrayList<>();
        mathGrades.forEach(mathGradesList::add);

        List<Grade> scienceGradesList = new ArrayList<>();
        scienceGrades.forEach(scienceGradesList::add);

        List<Grade> historyGradesList = new ArrayList<>();
        historyGrades.forEach(historyGradesList::add);

        studentGrades.setMathGradeResults(mathGradesList);
        studentGrades.setScienceGradeResults(scienceGradesList);
        studentGrades.setHistoryGradeResults(historyGradesList);

        return new GradebookStudent(student.get().getId(),
                student.get().getFirstname(), student.get().getLastname(), student.get().getEmailAddress(),
                studentGrades);
    }

    // stub method for cleaning up the project code
    public void configureStudentInformationModel(int id, Model m) {
        GradebookStudent gradebook = studentInformation(id);
        m.addAttribute("student", gradebook);
        if (!gradebook.getStudentGrades().getMathGradeResults().isEmpty()) {
            m.addAttribute("mathAverage", gradebook.getStudentGrades().findGradePointAverage(
                    gradebook.getStudentGrades().getMathGradeResults()
            ));
        } else m.addAttribute("mathAverage", "N/A");

        if (!gradebook.getStudentGrades().getScienceGradeResults().isEmpty()) {
            m.addAttribute("scienceAverage", gradebook.getStudentGrades().findGradePointAverage(
                    gradebook.getStudentGrades().getScienceGradeResults()
            ));
        } else m.addAttribute("scienceAverage", "N/A");

        if (!gradebook.getStudentGrades().getHistoryGradeResults().isEmpty()) {
            m.addAttribute("historyAverage", gradebook.getStudentGrades().findGradePointAverage(
                    gradebook.getStudentGrades().getHistoryGradeResults()
            ));
        } else m.addAttribute("historyAverage", "N/A");
    }
}
