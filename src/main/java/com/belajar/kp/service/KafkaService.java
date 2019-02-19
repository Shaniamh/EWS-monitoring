package com.belajar.kp.service;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.belajar.kp.controller.RestApiController;

public class KafkaService {
	  private String topicName;
	  Properties props = new Properties();
	  public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	  public KafkaService() {
	      props.put("bootstrap.servers", "master001.cluster02.bt:6667,namenode005.cluster02.bt:6667");
	      props.put("group.id", "S0607");
	      props.put("enable.auto.commit", "true");
	      props.put("auto.commit.interval.ms", "1000");
	      props.put("session.timeout.ms", "30000");
	      props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	      props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); 
	  }
	  public String Consumer() {
		  KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		  //Kafka Consumer subscribes list of topics here.
	      consumer.subscribe(Arrays.asList(topicName));
	      
	      //print the topic name
	      System.out.println("Subscribed to topic " + topicName);
	      int i = 0;
	      
	      while(true) {
	         ConsumerRecords<String, String> records = consumer.poll(60000);
	         if(records.isEmpty()) {
	        	 return("Tidak ada");
	         } else {
	        	 for (ConsumerRecord<String, String> record : records) {
	        		 if(i>=100)
	        			 break;
	        		 i++;
	        			 
	        		 //logger.info(record.value());
	        	 }
	        	 return("Ada");
	        		 
	         }
	         
	      }
	     
	  }

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	  
}
