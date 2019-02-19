package com.belajar.kp.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.belajar.kp.model.Status;
import com.belajar.kp.service.KafkaService;

@RestController
@RequestMapping("/api")
public class StreamKafka {
	public static final Logger logger = LoggerFactory.getLogger(StreamKafka.class);
	String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
	KafkaService kafka = new KafkaService();
	Status status = new Status(timeStamp);
	
	 @RequestMapping(value = "/send/", method = RequestMethod.GET)
	 @Scheduled(cron = "1 * * * * *")
	 @Scheduled(cron = "0 31 * * * *")
	    public ResponseEntity<?>sendToTelegram() {
	    	 kafka.setTopicName("tw-post-v3");
	    	 status.setDataKafka(kafka.Consumer());
	     	 Map<String, String> requestParams = new HashMap<>();
	     	requestParams.put("msg", status.toStringKafka());
	     	requestParams.put("to", "@Shaniamh");
	     	 RestTemplate restTemplate = new RestTemplate();
	     	 String url = restTemplate.getForObject("http://192.168.20.109:12601/send?msg={msg}&to={to}", String.class, requestParams);
	     	 return new ResponseEntity<Status>(status, HttpStatus.OK);
	     }
	 
	 
	     
}
