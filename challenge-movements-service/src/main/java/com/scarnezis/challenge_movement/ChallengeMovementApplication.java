package com.scarnezis.challenge_movement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChallengeMovementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeMovementApplication.class, args);
	}

}
