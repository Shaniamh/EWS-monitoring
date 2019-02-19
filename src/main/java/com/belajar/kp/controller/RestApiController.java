package com.belajar.kp.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.belajar.kp.model.Data;
import com.belajar.kp.model.Status;
import com.belajar.kp.model.Parent;
import com.belajar.kp.service.KafkaService;
import com.belajar.kp.service.UserService;
import com.belajar.kp.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {
	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
	@Autowired
    UserService userService; 
	String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
	String q = "Report Status Engine EWS-Patrol : ";
	//public static final String uri = "http://192.168.20.109:12601/send?";
	Status status = new Status(timeStamp);
	
	/*
	// -------------------Retrieve All Data--------------------------------------------- 
    @RequestMapping(value = "/data/", method = RequestMethod.GET)
    public ResponseEntity<?>listAllData() {
        List<Data> data = userService.findAllData();
        try {
        if (data.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        status.setStatus("not null");
        //return new ResponseEntity<List<Data>>(datas, HttpStatus.OK);
        }catch(NullPointerException e) {
        	status.setStatus("null");
        	//return new ResponseEntity(new CustomErrorType("Data kosong"),HttpStatus.NOT_FOUND);
        }
      return new ResponseEntity<Status>(status, HttpStatus.OK);
    }
    
    //semua
    @RequestMapping(value = "/coba/", method = RequestMethod.GET)
    public ResponseEntity<Parent>parent() {
        Parent user = userService.findAllParent();
    
        if (user==null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Parent>(user, HttpStatus.OK);
    }
    */
    
  //mengirim data
   
    @RequestMapping(value = "/kirim/", method = RequestMethod.GET)
    @Scheduled(cron = "1 01 * * * *")
    @Scheduled(cron = "0 31 * * * *")
    public ResponseEntity<?>sendToTelegram() {
    	 List<Data> data = userService.findAllData();
    	 try {
    		 if(data.isEmpty()) {
    			 return new ResponseEntity(HttpStatus.NO_CONTENT);
    		 }
    		 status.setMedsos(data.get(0).getMedsos());
    	 }catch(NullPointerException e) {
    		 status.setStatus("null");
    	 }
    	//gantinull
    	 Map<String, String> requestParams = new HashMap<>();
	    	requestParams.put("msg", status.toString());
	    	requestParams.put("to", "@Shaniamh");
	    	 RestTemplate restTemplate = new RestTemplate();
	    	 String url = restTemplate.getForObject("http://192.168.20.109:12601/send?msg={msg}&to={to}", String.class, requestParams);
    	 return new ResponseEntity<Status>(status, HttpStatus.OK);
    } 
    
 // -------------------Create a User-------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Data data, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", data);
        List<Data> datas= userService.findAllData();
        try {
        if (datas.isEmpty()) {
            userService.saveUser(data);
        }
        }catch(NullPointerException e) {
        	return new ResponseEntity(new CustomErrorType("Unable to create. A User " + " already exist."),HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(data.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
