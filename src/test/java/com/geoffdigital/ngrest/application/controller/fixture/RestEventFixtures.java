package com.geoffdigital.ngrest.application.controller.fixture;

import static com.geoffdigital.ngrest.application.controller.fixture.RestDataFixture.standardStudentDetails;
import static com.geoffdigital.ngrest.application.controller.fixture.RestDataFixture.customIdStudentDetails;

import java.util.Date;
import java.util.UUID;

import com.geoffdigital.ngrest.domain.events.students.StudentCreatedEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentDeletedEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentDetailsEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentStatusDetails;
import com.geoffdigital.ngrest.domain.events.students.StudentStatusEvent;

public class RestEventFixtures {

	public static StudentStatusEvent studentStatusNotFound(UUID id) {
		return StudentStatusEvent.notFound(id);
	}

	public static StudentStatusEvent studentStatus(UUID id, String status) {
		return new StudentStatusEvent(id, new StudentStatusDetails(new Date(), status));
	}

	public static StudentDetailsEvent studentDetailsNotFound(UUID id) {
		return StudentDetailsEvent.notFound(id);
	}

	public static StudentDetailsEvent studentDetailsEvent(UUID id) {
		return new StudentDetailsEvent(id, customIdStudentDetails(id));
	}

	public static StudentCreatedEvent studentCreated(UUID id) {
		return new StudentCreatedEvent(id, customIdStudentDetails(id));
	}

	public static StudentDeletedEvent studentDeleted(UUID id) {
		return new StudentDeletedEvent(id, standardStudentDetails());
	}

	public static StudentDeletedEvent studentDeletedFailed(UUID id) {
		return StudentDeletedEvent.deletionForbidden(id, standardStudentDetails());
	}

	public static StudentDeletedEvent studentDeletedNotFound(UUID id) {
		return StudentDeletedEvent.notFound(id);
	}
  
}
