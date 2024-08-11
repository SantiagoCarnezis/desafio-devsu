package com.scarnezis.desafio_devsu_api_service_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DesafioDevsuApiServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioDevsuApiServiceRegistryApplication.class, args);
	}

}
