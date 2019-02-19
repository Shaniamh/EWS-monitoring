package com.belajar.kp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.belajar.kp.model.Data;
import com.belajar.kp.model.Parent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	private static Parent parent;
	private static List<Data> data = new ArrayList<Data>();
	static{
    parent = new Parent();
    parent = populateDummyUsers();
    data = parent.getData();
	}
	

	private static Parent populateDummyUsers() {
		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource("http://103.75.25.60:4321/new-cyber-patrol-api/get-list-issue");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);
			ObjectMapper mapper = new ObjectMapper();
			parent = mapper.readValue(output, Parent.class);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return parent;
	}


	@Override
	public List<Data> findAllData() {
		try {
		return data;
		} catch(NullPointerException e) {
			return null;
		}
	}


	@Override
	public Parent findAllParent() {
		return parent;
	}


	@Override
	public void saveUser(Data datas) {
		datas.setId(counter.incrementAndGet());
		data.add(datas);
		
	}
}
