package com.belajar.kp.model;

public class Status {
	private String date;
	private String status;
	private String medsos;
	private String dataKafka;
	
	public String getDataKafka() {
		return dataKafka;
	}
	public void setDataKafka(String dataKafka) {
		this.dataKafka = dataKafka;
	}
	public Status(String date) {
		this.date=date;
		//this.medsos="TW";
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMedsos() {
		return medsos;
	}
	public void setMedsos(String medsos) {
		this.medsos = medsos;
	}
	
	public String toString() {
		return("Report Status Engine EWS-Patrol : "+"\\\\\\\\n"+"\\\\\\\\n"+"Date : "+ date +"\\\\\\\\n"+"Status : " + status+"\\\\\\\\n"+ "Medsos : "+ medsos + "\\\\\\\\n"+"Data Kafka : " + dataKafka);
	}
	
	public String toStringKafka() {
		return("Report Status Engine EWS-Patrol : "+"\\\\\\\\n"+"\\\\\\\\n"+"Date : "+ date +"\\\\\\\\n"+"Data Kafka : " + dataKafka);
	}
	
}
