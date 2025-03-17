Feature: Test reportes account

  Background:
    #* url 'http://localhost:8090/api/reportes/account'
    * def numeroCuentaMovimientos = '0dfce29d-ac55-4749-96eb-a898fcb19351'
    * def numeroCuentaSinMovimientos = '802fd05e-1323-4004-a2d7-db21eeb01879'
    * def fechaInicio = '2024/08/11 01:06:00'
    * def fechaFin = '2024/08/12 00:00:00'

  Scenario: Reportes account con movements
    Given url 'http://localhost:8090/api/reportes/account'
    And param numeroCuenta = numeroCuentaMovimientos
    And param fechaInicio = fechaInicio
    And param fechaFin = fechaFin
    When method GET
    Then status 200
    * print response
    And match response.codigo == 200
    And match response.mensaje == 'Reporte del state de las accounts del customer'
    And match response.data.numeroCuenta == numeroCuentaMovimientos
    And assert response.data.movements.length > 0

  Scenario: Reportes account sin movements
    Given url 'http://localhost:8090/api/reportes/account'
    And param numeroCuenta = numeroCuentaSinMovimientos
    And param fechaInicio = fechaInicio
    And param fechaFin = fechaFin
    When method GET
    Then status 200
    * print response
    And match response.codigo == 200
    And match response.mensaje == 'Reporte del state de las accounts del customer'
    And match response.data.numeroCuenta == numeroCuentaSinMovimientos
    And assert response.data.movements.length == 0
