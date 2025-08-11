package com.josecarlosroman.basics.spring.boot.jpademo;

import com.josecarlosroman.basics.spring.boot.jpademo.dao.AppDAO;
import com.josecarlosroman.basics.spring.boot.jpademo.entities.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaMappingApplication.class, args);
	}

	// command line application (executed after the Spring beans have been loaded)
	@Bean
	public CommandLineRunner commandLineRunner(String[] args, AppDAO appDAO) {
		return runner -> {
			System.out.println("Spring Boot - JPA mapping application");
			createInstructorAndCourses(appDAO); // to comment after creation
			//findInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//System.out.println("TEST: Courses BY student 1:" + appDAO.findCoursesJoinFetch(1));
			findInstructorWithCourses(appDAO);
			findCourse(appDAO);
			//Update...
			//deleteCourse(appDAO);
			//deleteInstructorDetail(appDAO);
			//deleteInstructor(appDAO);
			
		};
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting course with ID " + id);
		// will delete the reviews as well (cascadeType.ALL)
		// will NOT delete de students!
		appDAO.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void findCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("Finding course with ID " + id);
		Course course = appDAO.findCourseByIdJoinFetch(id);
		if (course != null) {
			System.out.println("Course: " + course);
			System.out.println("Reviews: " + course.getReviews());
			course.setStudents(appDAO.findStudentsJoinFetch(id));
			System.out.println("Students: " + course.getStudents());
		}
	}


	private void findInstructorWithCourses(AppDAO appDAO) {

		int id = 1;
		System.out.println("Finding instructor with ID " + id);

		// does NOT load the courses (lazy load by default)
		Instructor instructor = appDAO.findInstructorById(id);
		if (instructor != null) {
			System.out.println("Instructor: " + instructor);
			// System.out.println("Courses: " + instructor.getCourses());
			// getCourses fails (lazy load: we only loaded the instructor)
			List<Course> courses = appDAO.findCourses(instructor.getId());
			instructor.setCourses(courses);
			// it works now, but it requires a new query to the database
			// SOLUTION: FETCH JOIN, we have that new method to get them
			// we are still not loading all the courses by default
			//Instructor instructorWithCourses = appDAO.findInstructorByIdJoinFetch(id);
			System.out.println("Courses: " + instructor.getCourses());
			System.out.println("Done!");
		}
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 3;
		System.out.println("Deleting instructor with detail ID " + id);

		appDAO.deleteInstructorDetailById(id);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 1;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("Finding instructor detail with ID: " + id);
		if (instructorDetail != null) {
			System.out.println("Instructor: " + instructorDetail.getInstructor());

			System.out.println("Done!");
		}
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting instructor with ID: " + id);

		appDAO.deleteInstructorById(id);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {

		int id = 1;
		System.out.println("Finding instructor with ID: " + id);

		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("Instructor: " + instructor);
	}

	private void createInstructorAndCourses(AppDAO appDAO) {

		Instructor instructor = new Instructor("Chab", "Darby", "darby@luv2code.com");
		InstructorDetail instructorDetail = new InstructorDetail(
				"http:://www.luv2code.con/youtube",
				"Coding");
		instructor.setInstructorDetail((instructorDetail));
		Course course = new Course("Spring and Hibernate");
		course.addReview(new Review("Awesome!!!"));
		course.addReview(new Review("Good job, my friend!!!"));
		course.addStudent(new Student("John", "Doe", "johndoe@email.com"));
		course.addStudent(new Student("Jane", "Doe", "janedoe@email.com"));
		instructor.add(course);
		instructor.add(new Course("Spring Boot Unit Testing"));

		System.out.println("Saving instructor: " + instructor);
		appDAO.save(instructor);
		System.out.println("Done!");
	}

}
