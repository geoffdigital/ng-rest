package com.geoffdigital.ngrest.domain.events.students;

import com.geoffdigital.ngrest.domain.events.UpdateEvent;

import java.util.UUID;

public class UpdateStudentDetailsEvent extends UpdateEvent {

	private UUID id;
	private StudentDetails studentDetails;

	public UpdateStudentDetailsEvent(UUID id, StudentDetails studentDetails) {
		this.id = id;
		this.studentDetails = studentDetails;
	}

	public UUID getId() {
		return id;
	}

	public StudentDetails getStudentDetails() {
		return studentDetails;
	}
	
}
