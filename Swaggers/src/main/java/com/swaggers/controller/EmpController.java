package com.swaggers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swaggers.entity.Employee;
@RestController
public class EmpController {

	
	ConcurrentMap<String, Employee>map=new ConcurrentHashMap<>();
	
	@PostMapping("/save")
	public Employee saveData(@RequestBody Employee employee)
	{
		
	 return	employee;
	//	return map.put(employee.getName(), employee);
		
	}
	@GetMapping("/")
	public List<Employee>getAllEmp()
	{
		
		//return (List<Employee>) map.values();
		return new ArrayList<Employee>(map.values());
	}
	
	@GetMapping("/{id}")
	public Employee getById(@PathVariable("id") int id)
	{
		
		
		
		return map.get(id);
	}
	
}
