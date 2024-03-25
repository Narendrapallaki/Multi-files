package com.springdto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdto.Repository.StudentRep;
import com.springdto.Repository.TeacherRepo;
import com.springdto.dto.StuTeacherDto;
import com.springdto.model.Student;
import com.springdto.model.Teacher;

@Service
public class ServiceImp {
	
	@Autowired
	private TeacherRepo teacherRepo;
	
	@Autowired
	private StudentRep studentRep;
	
	
	//private ModelMapper mapper;
	
/*	public List<StuTeacherDto>getAll()
	{
		
		return studentRep.findAll()
				.stream().map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}
	
	private StuTeacherDto convertEntityToDto(Student student) {

		StuTeacherDto dto = new StuTeacherDto();
		dto.setSId(student.getId());
		dto.setSName(student.getName());
		dto.setSClass(student.getClassName());
		dto.setSSchoolName(student.getTeacher().getSchoolName());
		dto.setSTeacherName(student.getTeacher().getName());

		return dto;

	}*/
	
	
	public List<Teacher>get(String name)
	{
		return teacherRepo.findByName(name);
	}
  
	
	public Student getStu(int name)
	{
		Student byId = studentRep.findById(name).get();
		
		return byId;
 	}

}
