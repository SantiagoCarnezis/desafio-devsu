package com.scarnezis.challenge_user.config;

import feign.RetryableException;
import feign.Retryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DevsuRetryer implements Retryer {

    private static final Logger logger = LoggerFactory.getLogger(DevsuRetryer.class);

    private final int maxAttempts = 3;
    private final long backoff = 2000;
    private int attempts;

    public DevsuRetryer() {
        this.attempts = 1;
    }

    @Override
    public void continueOrPropagate(RetryableException ex) {

        if (attempts++ >= maxAttempts) {
            throw ex;
        }

        logger.warn("Failed attempts: " +  (attempts - 1));

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
