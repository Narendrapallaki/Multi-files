package com.testing.testingContro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/year")
public class TestingContro {
	
	
	@GetMapping("/month")
	public String testing()
	{
		return "this securing is running...!";
	}

}
