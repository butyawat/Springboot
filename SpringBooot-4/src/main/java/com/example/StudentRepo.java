package com.example;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Integer> {
	public Student findById(int id);

}
