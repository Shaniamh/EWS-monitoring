package com.belajar.kp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.belajar.kp.model.Data;
import com.belajar.kp.model.Parent;
import com.belajar.kp.service.UserServiceImpl;

@EnableScheduling
@SpringBootApplication(scanBasePackages={"com.belajar.kp"})
public class KpApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpApplication.class, args);
	}
}

