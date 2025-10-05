package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentservice;
	@GetMapping("getdata")
	public List<Student> getAll(){
		return this.studentservice.getAllStudents();
	}
	@GetMapping("getdata/{id}")
	public Student getSingleData(@PathVariable("id") int id) {
		return this.studentservice.getStudentById(id);
	}
	@PostMapping("getdata")
	public Student addStudent(@RequestBody Student student) {
		Student s = this.studentservice.addStudent(student);
		System.out.println(s);
		return s;
	}
	@DeleteMapping("getdata/{studentId}")
	public void deleteData(@PathVariable("studentId")int studentId) {
		this.studentservice.deleteStudent(studentId);
	}
	@PutMapping("getdata/{studentId}")
	public Student updataStudent(@RequestBody Student student,@PathVariable("studentId")int studentId) {
		this.studentservice.updateStudent(student, studentId);
		return student;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
