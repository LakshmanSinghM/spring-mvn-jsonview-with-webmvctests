// Practical assignment – Spring MVC – json view
// Develop a RESTful API to manage information about users and their orders in an online store.
//  Use the @JsonView annotation to define different JSON representations depending on the context.




package com.lakshman.springmvc_json;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
}