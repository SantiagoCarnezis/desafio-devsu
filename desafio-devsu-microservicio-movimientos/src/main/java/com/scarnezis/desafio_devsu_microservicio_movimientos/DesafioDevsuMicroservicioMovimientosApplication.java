package com.scarnezis.desafio_devsu_microservicio_movimientos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioDevsuMicroservicioMovimientosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioDevsuMicroservicioMovimientosApplication.class, args);
	}

}
