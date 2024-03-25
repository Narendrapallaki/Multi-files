package com.springdto;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springdto.Repository.StudentRep;
import com.springdto.Repository.TeacherRepo;
import com.springdto.model.Student;
import com.springdto.model.Teacher;



@SpringBootApplication
public class SpringDtoWorkApplication implements CommandLineRunner {

	// implements CommandLineRunner
	 @Autowired
     private StudentRep studentRep;
     @Autowired
     private TeacherRepo  teacherRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDtoWorkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		

		Teacher t1=new Teacher();
		t1.setName("bhavane");
		t1.setSubject("Hr");
		t1.setSchoolName("eidiko");
		teacherRepo.save(t1);
		
		Student stu=new Student();
		stu.setAge(12);
		stu.setClassName("10th class");
		stu.setName("Narendra");
		stu.setTeacher(null);
		
		
		studentRep.save(stu);
		
	}

}
