
package com.kafka.KafkaProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kafka.entity.User;

@Service
public class JsonKafkaMessage {

	private static final Logger log = LoggerFactory.getLogger(JsonKafkaMessage.class);

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	public void sendMessage(User user) {
		log.info("Json kafka message {}", user);

		Message<User> message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, "springNewTopic_json")
				.build();

		kafkaTemplate.send(message);
	}

}
