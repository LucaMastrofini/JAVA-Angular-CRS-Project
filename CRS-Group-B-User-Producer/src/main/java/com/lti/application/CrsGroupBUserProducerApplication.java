package com.lti.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.lti.*"})
@EnableWebMvc
@EnableDiscoveryClient
public class CrsGroupBUserProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrsGroupBUserProducerApplication.class, args);
	}

}
