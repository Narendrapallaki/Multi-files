package com.kafka.kafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	public NewTopic springNewTopic() {
		return TopicBuilder.name("springNewTopic").build();
	}

	@Bean
	public NewTopic springNewJsonTopic() {
		return TopicBuilder.name("springNewTopic_json").build();
	}

}
