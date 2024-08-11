package com.scarnezis.desafio_devsu_microservicio_movimientos;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

class ReporteCuentaConMovimientosTest {

    @EnabledIfSystemProperty(named = "runTests", matches = "true")
    @Karate.Test
    Karate testReporteCuenta() {

        return Karate.run("classpath:features/reportes.feature");
    }
}
