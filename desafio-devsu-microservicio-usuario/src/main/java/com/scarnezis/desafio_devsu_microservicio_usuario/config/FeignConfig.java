package com.scarnezis.desafio_devsu_microservicio_usuario.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Retryer retryer() {

        return new DevsuRetryer();
    }
}
