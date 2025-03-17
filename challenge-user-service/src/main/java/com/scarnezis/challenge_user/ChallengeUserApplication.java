package com.scarnezis.challenge_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChallengeUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeUserApplication.class, args);
	}

}
