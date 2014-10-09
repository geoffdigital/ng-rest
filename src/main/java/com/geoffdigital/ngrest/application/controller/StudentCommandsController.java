package com.geoffdigital.ngrest.application.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.geoffdigital.ngrest.application.services.StudentService;
import com.geoffdigital.ngrest.domain.events.students.CreateStudentEvent;
import com.geoffdigital.ngrest.domain.events.students.DeleteStudentEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentCreatedEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentDeletedEvent;
import com.geoffdigital.ngrest.domain.model.Student;

@Controller
@RequestMapping("/students")
public class StudentCommandsController {

	private static Logger LOG = LoggerFactory.getLogger(StudentCommandsController.class);

    @Autowired
    private StudentService studentService;

    // Create Student 
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> createStudent(@RequestBody Student student, UriComponentsBuilder builder) {

        StudentCreatedEvent studentCreated = studentService.createStudent(new CreateStudentEvent(student.toStudentDetails()));

        Student newStudent = Student.fromStudentDetails(studentCreated.getDetails());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/students/{id}")
                        .buildAndExpand(studentCreated.getNewStudentId().toString()).toUri());

        return new ResponseEntity<Student>(newStudent, headers, HttpStatus.CREATED);
    }

    // Delete Student
    @RequestMapping(method = RequestMethod.DELETE, value = "students/{id}")
    public ResponseEntity<Student> cancelStudent(@PathVariable String id) {

        StudentDeletedEvent studentDeleted = studentService.deleteStudent(new DeleteStudentEvent(UUID.fromString(id)));

        if (!studentDeleted.isEntityFound()) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        Student student = Student.fromStudentDetails(studentDeleted.getDetails());

        if (studentDeleted.isDeletionCompleted()) {
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }

        return new ResponseEntity<Student>(student, HttpStatus.FORBIDDEN);
    }
}
