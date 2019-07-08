package com.sg.KafkaPublisher;

import com.sg.KafkaPublisher.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class KafkaPublisherApplication {

	@Autowired
	KafkaTemplate<String,Object> template;

	private String topic="testing-topic";

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}


	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name){
		template.send(topic,"Hi! " +name+ "..Testing 123..!!");
		return "Data-Published";
	}

	@GetMapping("/publishJson")
	public String publishMessage(){
		User user=new User(1,"User 1",new String[]{"Testing Address","Testing Country"});
		template.send(topic,user);
		return "Json data Published";
	}


}
