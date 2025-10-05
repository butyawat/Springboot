package com.example;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBooot2Application {

	public static void main(String[] args) {
		ApplicationContext run = SpringApplication.run(SpringBooot2Application.class, args);
		StudentRepo bean = run.getBean(StudentRepo.class);
		
		/* Student so1 = new Student();
		so1.setName("Rajput");
		so1.setCity("Indore");
		//  Student save = bean.save(so1); // for storing only one student data 
		
		Student so2 = new Student();
		so2.setName("Niharika");
		so2.setCity("Delhi");
		Student so3 = new Student();
		so3.setName("Neerja");
		so3.setCity("Gujrat");
		Student so4 = new Student();
		so4.setName("Pulkit");
		so4.setCity("Bhopal");
		List<Student> alldata = List.of(so1,so2, so3, so4);
		Iterable<Student> saveAll = bean.saveAll(alldata);
		
		saveAll.forEach(s1->{
			System.out.println(s1);
		});*/

		
		
		
		// for deleting one data by id
		/* bean.deleteById(5);
		System.out.println("Data is deleted");*/
		
		// for deleting all data by id
		
		/*
		Iterable<Student> all = bean.findAll();
		bean.deleteAll(all);*/
		
		
		
		//Users se input lena
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean go1 = true;
		while(go1) {
			System.out.println("Enter 1 to Add new Student Data");
			System.out.println("Enter 2 to display new Student Data");
			System.out.println("Enter 3 to Delete single Student Data");
			System.out.println("Enter 4 to Delete all Student Data");
			System.out.println("Enter 5 to update Student Data");
			
			
			try {
				int choice = Integer.parseInt(br.readLine());
				switch(choice) {
				case 1:
					System.out.println("Enter Student Name");
					String sName = br.readLine();
					System.out.println("Enter Student City");
					String cName = br.readLine();
					
					Student s1 = new Student();
					s1.setName(sName);
					s1.setCity(cName);
					Student save = bean.save(s1);
					System.out.println("Data saved");
					break;
				case 2:
					System.out.println("====================");
					Iterable<Student> all = bean.findAll();
					Iterator<Student> iterator = all.iterator();
					while(iterator.hasNext()) {
						Student next = iterator.next();
						System.out.println(next);
					}
					break;
				case 3:
					System.out.println("Enter Id to Delete Student Data");
					int sId = Integer.parseInt(br.readLine());
					bean.deleteById(sId);
					System.out.println("Data Deleted");
					break;
				case 4:
					Iterable<Student> all2 = bean.findAll();
					bean.deleteAll(all2);
					System.out.println("Data Deleted");
					break;
				case 5:
					System.out.println("Enter Id to update Student Data");
					int Idu= Integer.parseInt(br.readLine());
					System.out.println("Enter New Student Name");
					String newName = br.readLine();
					System.out.println("Enter New Student City");
					String newCity = br.readLine();
					Optional<Student> byId = bean.findById(Idu);
					Student student = byId.get();
					student.setName(newName);
					student.setCity(newCity);
					Student save2 = bean.save(student);
					System.out.println("Data is updated");
					break;
					default:
						System.out.println("Invlalid Number");
					
					
				}
			}
			catch(Exception e) {
				System.out.println("Invalid input try again");
				System.out.println(e.getMessage());
			}
			
		}
		
		
		
		
	}

}
