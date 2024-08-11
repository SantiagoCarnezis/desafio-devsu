package com.scarnezis.desafio_devsu_microservicio_usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioDevsuMicroservicioUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioDevsuMicroservicioUsuarioApplication.class, args);
	}

}
