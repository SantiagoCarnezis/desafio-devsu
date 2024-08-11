package com.scarnezis.desafio_devsu_microservicio_usuario.config;

import feign.RetryableException;
import feign.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DevsuRetryer implements Retryer {

    private static final Logger logger = LoggerFactory.getLogger(DevsuRetryer.class);

    private final int intentosMaximos = 3;
    private final long backoff = 2000;
    private int cantidadIntentos;

    public DevsuRetryer() {
        this.cantidadIntentos = 1;
    }

    @Override
    public void continueOrPropagate(RetryableException ex) {

        if (cantidadIntentos++ >= intentosMaximos) {
            throw ex;
        }

        logger.warn("Intento fallido numero " +  (cantidadIntentos - 1));

        try {
            Thread.sleep(backoff);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Retryer clone() {
        return new DevsuRetryer();
    }
}
