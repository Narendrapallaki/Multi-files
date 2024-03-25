
package com.kafka.KafkaProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.entity.User;

@Service
public class JsonKafkaConsumer {

	private static final Logger log = LoggerFactory.getLogger(JsonKafkaConsumer.class);

	@KafkaListener(topics = "springNewTopic_json", groupId = "group_id")
	public void jsonKafkaConsum(User user) {
		log.info("Json kafka consumer {}", user);
	}

}
