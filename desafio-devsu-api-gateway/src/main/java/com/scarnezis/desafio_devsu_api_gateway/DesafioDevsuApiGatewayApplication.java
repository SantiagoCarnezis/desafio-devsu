package com.scarnezis.desafio_devsu_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DesafioDevsuApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioDevsuApiGatewayApplication.class, args);
	}

}
