package com.project.utility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootProjectUtilityConceptsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectUtilityConceptsApplication.class, args);
	}

}
