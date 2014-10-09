package com.geoffdigital.ngrest.domain.model.fixtures;

import com.geoffdigital.ngrest.domain.events.students.StudentDetails;
import com.geoffdigital.ngrest.domain.model.Student;

public class StudentsFixtures {

	public static final String FIRST_NAME = "John";
	public static final String LAST_NAME = "Smith";
	public static final String EMAIL = "john@smith.com";
	public static final String MAJOR = "English";

	public static Student standardStudent() {
		Student student = new Student();

		student.setFirstName(FIRST_NAME);
		student.setLastName(LAST_NAME);
		student.setEmail(EMAIL);
		student.setMajor(MAJOR);

		return student;
	}

	public static StudentDetails standardStudentDetails() {
		return standardStudent().toStudentDetails();
	}

}
