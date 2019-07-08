package com.sg.KafkaConsumer;

import com.sg.KafkaConsumer.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@SpringBootApplication
public class SpringConsumerApplication {

	List<String> messages=new ArrayList<>();
	User user=null;

	public static void main(String[] args) {
		SpringApplication.run(SpringConsumerApplication.class, args);
	}

	@KafkaListener(groupId = "test-topic-1",topics = "testing-topic",containerFactory = "kafkaListenerContainerFactory")
	public List<String> getStringMessageFromTopic(String data){
		messages.add(data);
		return messages;
	}

	@GetMapping("/consumeStringMessage")
	public List<String> consumeStringMessage(){
		return messages;
	}


	@KafkaListener(groupId = "test-topic-2",topics = "testing-topic",containerFactory = "userKafkaListenerContainerFactory")
	public User getJSONMessageFromTopic(User user){
		this.user=user;
		return this.user;
	}

	@GetMapping("/consumeJsonMessage")
	public User consumeJSONMessage(){
		return this.user;
	}

}
