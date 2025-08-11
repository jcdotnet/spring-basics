package com.josecarlosroman.basics.spring.boot.jpademo.dao;

import com.josecarlosroman.basics.spring.boot.jpademo.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // save will also save the details because of the CASCADE all
    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    // will also retrieve the details because default behavior of
    // @OneToOne default fetch type is EAGER and won't retrieve the
    // courses because the @OneToMany fetch default type is LAZY
    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        "JOIN FETCH i.courses JOIN FETCH i.instructorDetail " +
                        "WHERE i.id=:data", Instructor.class
        );
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }
    @Override
    public List<Course> findCourses(int instructorId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );
        query.setParameter("data", instructorId);
        return query.getResultList();
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public Course findCourseByIdJoinFetch(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "JOIN FETCH c.reviews " +
                        "WHERE c.id=:data", Course.class
        );
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public List<Student> findStudentsJoinFetch(int courseId) {
        TypedQuery<Student> query = entityManager.createQuery(
                "from Student s " +
                        "JOIN FETCH s.courses c " +
                        "WHERE c.id=:data", Student.class
        );
        query.setParameter("data", courseId);
        return query.getResultList();
    }

    @Override
    public List<Course> findCoursesJoinFetch(int studentId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course c " +
                        "JOIN FETCH c.students s " +
                        "WHERE s.id=:data", Course.class
        );
        query.setParameter("data", studentId);
        return query.getResultList();
    }


    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    // will also delete the details because of the CASCADE all
    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        if (instructor != null) {
            List<Course> courses = instructor.getCourses();
            for (Course course : courses) {
                course.setInstructor(null);
            }
            entityManager.remove(instructor);
        }
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {

        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        if (instructorDetail != null) {
            instructorDetail.getInstructor().setInstructorDetail(null);
            entityManager.remove(instructorDetail);
        }
    }

    @Override
    public void deleteStudentById(int id) {
        Student student = entityManager.find(Student.class, id);

        if (student != null) {
            List<Course> courses = student.getCourses();

            for (Course course : courses) {
                course.getStudents().remove(student); // List
            }
            entityManager.remove(student); // DB
        }
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        if (course != null) {
            entityManager.remove(course);
        }
    }
}
