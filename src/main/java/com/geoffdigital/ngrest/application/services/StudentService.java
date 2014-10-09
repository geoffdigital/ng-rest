package com.geoffdigital.ngrest.application.services;

import com.geoffdigital.ngrest.domain.events.students.AllStudentsEvent;
import com.geoffdigital.ngrest.domain.events.students.CreateStudentEvent;
import com.geoffdigital.ngrest.domain.events.students.DeleteStudentEvent;
import com.geoffdigital.ngrest.domain.events.students.RequestAllStudentsEvent;
import com.geoffdigital.ngrest.domain.events.students.RequestStudentDetailsEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentCreatedEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentDeletedEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentDetailsEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentUpdatedEvent;
import com.geoffdigital.ngrest.domain.events.students.UpdateStudentDetailsEvent;

public interface StudentService {

	public AllStudentsEvent requestAllStudents(RequestAllStudentsEvent requestAllCurrentStudentsEvent);

	public StudentDetailsEvent requestStudentDetails(RequestStudentDetailsEvent requestStudentDetailsEvent);

	public StudentCreatedEvent createStudent(CreateStudentEvent event);

	public StudentUpdatedEvent updateStudentDetails(UpdateStudentDetailsEvent updateStudentDetailsEvent);

	public StudentDeletedEvent deleteStudent(DeleteStudentEvent deleteStudentEvent);

}
