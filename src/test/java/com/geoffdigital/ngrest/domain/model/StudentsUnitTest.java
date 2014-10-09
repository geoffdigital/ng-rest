package com.geoffdigital.ngrest.domain.model;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.geoffdigital.ngrest.domain.model.fixtures.StudentsFixtures;
import com.geoffdigital.ngrest.infrastructure.repository.StudentsMemoryRepository;

public class StudentsUnitTest {

  StudentsMemoryRepository uut;

  @Before
  public void setupUnitUnderTest() {
    Map<UUID, Student> emptyStudentList = new HashMap<UUID, Student>();
    uut = new StudentsMemoryRepository(emptyStudentList);
  }

  @Test
  public void addASingleStudentToTheStudents() {

    assertEquals(0, uut.findAll().size());

    uut.save(StudentsFixtures.standardStudent());

    assertEquals(1, uut.findAll().size());
  }

  @Test
  public void removeASingleStudent() {

    UUID id = UUID.randomUUID();

    uut = new StudentsMemoryRepository(Collections.singletonMap(id, StudentsFixtures.standardStudent()));

    assertEquals(1, uut.findAll().size());

    uut.delete(id);

    assertEquals(0, uut.findAll().size());
  }
}
