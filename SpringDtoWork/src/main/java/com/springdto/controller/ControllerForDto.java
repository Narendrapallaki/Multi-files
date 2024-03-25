package com.springdto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springdto.dto.StuTeacherDto;
import com.springdto.model.Student;
import com.springdto.model.Teacher;
import com.springdto.service.ServiceImp;

@RestController
public class ControllerForDto {
	
	
	
	@Autowired
	private ServiceImp serviceImp;
     
/*	@GetMapping("/getData")
	public ResponseEntity<List<StuTeacherDto>> response()
	{
		      List<StuTeacherDto> all = serviceImp.getAll();
		
		return new ResponseEntity<List<StuTeacherDto>>(all, HttpStatus.OK);
	}*/
	
	
	@GetMapping("/getData/{name}")
	public ResponseEntity< List<Teacher>> getTeacher(@PathVariable("name") String name)
	{
		      List<Teacher> all = serviceImp.get(name);
		
		return new ResponseEntity< List<Teacher>>(all, HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Student> getStu(@PathVariable("id") int name)
	{
		    Student all = serviceImp.getStu(name);
		
		return new ResponseEntity<Student>(all, HttpStatus.OK);
	}
}
