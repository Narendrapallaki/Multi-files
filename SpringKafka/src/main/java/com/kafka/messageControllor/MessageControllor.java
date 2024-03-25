package com.kafka.messageControllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.KafkaProducer.KafkaProducer;

@RestController
@RequestMapping("/kafka/message")
public class MessageControllor {
	
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	//localhost:8080/kafka/message/publish
	@GetMapping("/publish")
	public ResponseEntity<String>sendMessage(@RequestParam("message") String message)
	{
		
		
		kafkaProducer.send(message);
		return ResponseEntity.ok("Message send to the topic....!");
	}
	

}
