package com.cts.stockmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataPublisher {
	
public static final String topic= "companysheet";
	
	@Autowired
	public KafkaTemplate<String, String> template;
	
	public KafkaTemplate<String, String> getTemplate() {
		return template;
	}

	public void setTemplate(String update) {
		System.out.println("Publishing to topic:"+ topic);
		this.template.send(topic, update);
	}

	public static String getTopic() {
		return topic;
	}


}
