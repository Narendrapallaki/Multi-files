
package com.kafka.messageControllor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.KafkaProducer.JsonKafkaMessage;
import com.kafka.entity.User;

@RestController

@RequestMapping("/kafka/jsonMessage")
public class JsonMessageControllor {

	private static final Logger log = LoggerFactory.getLogger(JsonMessageControllor.class);

	@Autowired
	private JsonKafkaMessage jsonKafkaMessage;

	@PostMapping("/sendJsonMessage")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
		log.info("Json message controllor {}", user);
		jsonKafkaMessage.sendMessage(user);
		return ResponseEntity.ok("Json message sended....!");
	}
}
