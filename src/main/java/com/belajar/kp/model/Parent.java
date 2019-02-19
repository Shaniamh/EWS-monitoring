package com.belajar.kp.model;

import java.util.ArrayList;
import java.util.List;

public class Parent {
	private String status;
	private String message;
	private List<Data> data = new ArrayList<Data>();
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	
}
