package com.geoffdigital.ngrest.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import com.geoffdigital.ngrest.domain.model.Student;

public interface StudentsRepository {

  Student save(Student student);

  void delete(UUID id);

  Student findById(UUID id);

  List<Student> findAll();
  
}
