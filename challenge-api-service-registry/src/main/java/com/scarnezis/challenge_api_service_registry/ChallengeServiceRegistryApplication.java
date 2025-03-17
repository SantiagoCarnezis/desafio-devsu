package com.scarnezis.challenge_api_service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ChallengeServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeServiceRegistryApplication.class, args);
	}

}
