package com.geoffdigital.ngrest.application.controller.fixture;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.geoffdigital.ngrest.domain.events.students.AllStudentsEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentDetails;
import com.geoffdigital.ngrest.domain.model.Student;

public class RestDataFixture {

	public static final String FIRST_NAME = "John";
	public static final String LAST_NAME = "Smith";
	public static final String EMAIL = "john@smith.com";
	public static final String MAJOR = "English";

	public static AllStudentsEvent allStudents() {
		List<StudentDetails> students = new ArrayList<StudentDetails>();

		students.add(standardStudentDetails());
		students.add(standardStudentDetails());
		students.add(standardStudentDetails());

		return new AllStudentsEvent(students);
	}

	public static Student standardStudent() {
		Student student = new Student();

		student.setFirstName(FIRST_NAME);
		student.setLastName(LAST_NAME);
		student.setEmail(EMAIL);
		student.setMajor(MAJOR);

		return student;
	}

	public static StudentDetails customIdStudentDetails(UUID id) {
		StudentDetails studentDetails = new StudentDetails(id);

		studentDetails.setFirstName(FIRST_NAME);
		studentDetails.setLastName(LAST_NAME);
		studentDetails.setEmail(EMAIL);
		studentDetails.setMajor(MAJOR);

		return studentDetails;
	}
	
	public static StudentDetails standardStudentDetails() {
		return customIdStudentDetails(UUID.randomUUID());
	}

	public static String standardStudentJSON() {
		return "{ \"items\": { \"yummy1\": 12, \"yummy15\": 42 } }";
	}

}
