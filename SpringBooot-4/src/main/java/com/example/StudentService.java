package com.example;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentService {
	@Autowired
	private StudentRepo studentrepo;
	
	public List<Student> getAllStudents(){
		List<Student> list = (List<Student>) this.studentrepo.findAll();
		return list;
	}
	public Student getStudentById(int id) {
		Student student = null;
		student = this.studentrepo.findById(id);
		return student;
	}
	public Student addStudent(Student s) {
		Student save = this.studentrepo.save(s);
		return save;
	}
	public void deleteStudent(int sid) {
		this.studentrepo.deleteById(sid);
	}
	public void updateStudent(Student student, int studentId) {
		student.setId(studentId);
		this.studentrepo.save(student);
	}
	
	
	
}
