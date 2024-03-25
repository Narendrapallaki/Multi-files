package com.swaggers.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swaggers.entity.Employee;

@Service
public class EmpService {

	
	
	public Employee save()
	{
		
		Employee emp=new Employee(1, "narendra", "eidiko");
		
		return emp;
	}
	
	public List<Employee>list()
	{
		Employee emp=new Employee();
		
		
		return (List<Employee>) emp;
	}
	
	public List<Employee>findById(int id)
	{
		return null;
	}
}
