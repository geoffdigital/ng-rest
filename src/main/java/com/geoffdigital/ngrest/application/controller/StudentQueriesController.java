package com.geoffdigital.ngrest.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.geoffdigital.ngrest.application.services.StudentService;
import com.geoffdigital.ngrest.domain.events.students.RequestAllStudentsEvent;
import com.geoffdigital.ngrest.domain.events.students.RequestStudentDetailsEvent;
import com.geoffdigital.ngrest.domain.events.students.StudentDetails;
import com.geoffdigital.ngrest.domain.events.students.StudentDetailsEvent;
import com.geoffdigital.ngrest.domain.model.Student;

@Controller
@RequestMapping("/students")
public class StudentQueriesController {

	private static Logger LOG = LoggerFactory.getLogger(StudentQueriesController.class);

    @Autowired
    private StudentService studentService;

    // View All Students
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();
        for (StudentDetails detail : studentService.requestAllStudents(new RequestAllStudentsEvent()).getStudentsDetails()) {
            students.add(Student.fromStudentDetails(detail));
        }
        return students;
    }

    // View Student
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Student> viewStudent(@PathVariable String id) {

        StudentDetailsEvent details = studentService.requestStudentDetails(new RequestStudentDetailsEvent(UUID.fromString(id)));

        if (!details.isEntityFound()) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        Student student = Student.fromStudentDetails(details.getStudentDetails());

        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
    
}
