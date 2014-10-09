package com.geoffdigital.ngrest.infrastructure.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.geoffdigital.ngrest.domain.model.Student;

public class StudentsMemoryRepository implements StudentsRepository {

	private Map<UUID, Student> students;

	public StudentsMemoryRepository(final Map<UUID, Student> students) {
		this.students = Collections.unmodifiableMap(students);
	}

	@Override
	public synchronized Student save(Student student) {
		Map<UUID, Student> modifiableStudents = new HashMap<UUID, Student>(students);
		modifiableStudents.put(student.getId(), student);
		this.students = Collections.unmodifiableMap(modifiableStudents);

		return student;
	}

	@Override
	public synchronized void delete(UUID id) {
		if (students.containsKey(id)) {
			Map<UUID, Student> modifiableStudents = new HashMap<UUID, Student>(students);
			modifiableStudents.remove(id);
			this.students = Collections.unmodifiableMap(modifiableStudents);
		}
	}

	@Override
	public Student findById(UUID id) {
		return students.get(id);
	}

	@Override
	public List<Student> findAll() {
		return Collections.unmodifiableList(new ArrayList<Student>(students.values()));
	}
	
}
