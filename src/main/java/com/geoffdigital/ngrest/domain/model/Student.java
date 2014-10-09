package com.geoffdigital.ngrest.domain.model;

import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.geoffdigital.ngrest.domain.events.students.StudentDetails;

public class Student {
	
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private String major;
	
	public Student() {
		this.id = UUID.randomUUID();
	}
	
	public boolean canBeDeleted() {
	    return true;
	}
	
	public StudentDetails toStudentDetails() {
		StudentDetails details = new StudentDetails();

	    BeanUtils.copyProperties(this, details);

	    return details;
	}

	public static Student fromStudentDetails(StudentDetails studentDetails) {
	    Student student = new Student();

	    BeanUtils.copyProperties(studentDetails, student);

	    return student;
	}
	
	public UUID getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
}
