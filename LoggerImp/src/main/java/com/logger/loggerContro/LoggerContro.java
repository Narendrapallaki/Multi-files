package com.logger.loggerContro;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoggerContro {

	
	@GetMapping("/")
	public ResponseEntity<String>res()
	{
		Map<String, Integer>data=new HashMap<>();
		
		data.put("Nari", 3);
		data.put("Ram", 6);
		log.info("Hash map result :{}",data);
		return ResponseEntity.ok("Working...!");
	}
	
}
