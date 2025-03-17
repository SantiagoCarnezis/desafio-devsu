package com.scarnezis.challenge_movement;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

class ReporteAccountConMovimientosTest {

    @EnabledIfSystemProperty(named = "runTests", matches = "true")
    @Karate.Test
    Karate testReporteCuenta() {

        return Karate.run("classpath:features/reportes.feature");
    }
}
