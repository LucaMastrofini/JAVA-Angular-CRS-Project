package com.lti.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CrsGroupBEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupBEurekaServerApplication.class, args);
	}

}
