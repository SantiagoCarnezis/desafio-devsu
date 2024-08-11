package com.scarnezis.desafio_devsu_microservicio_movimientos.dto;

public class Mensajes {

    // Respuestas Exitosas

    public static final String CUENTA_ENCONTRADA = "Cuentas encontradas exitosamente";
    public static final String CUENTA_CREADA = "Cuenta creada";
    public static final String CUENTA_ELIMINADA = "Cuenta eliminada";

    public static final String MOVIMIENTOS_DE_LA_CUENTA = "Movimientos de la cuenta";
    public static final String MOVIMIENTO_ENCONTRADO = "Movimiento encontrado";
    public static final String PAGO_REALIZADO = "Pago realizado";

    public static final String REPORTES_ENCONTRADOS = "Reporte del estado de las cuentas del cliente";
    public static final String CLIENTE_SIN_CUENTAS = "El cliente aun no tiene cuentas";
    public static final String CUENTA_SIN_MOVIMIENTOS = "La cuenta aun no tiene movimientos realizados";

    // Errores
    public static final String CUENTA_NO_ENCONTRADA = "No se encontro la cuenta de numero ";
    public static final String SALDO_INSUFICIENTE = "No hay saldo suficiente en la cuenta ";
    public static final String CUENTA_CERRADA_REALIZAR_PAGO = "No se puede realizar un pago con una cuenta cerrada";
    public static final String CUENTA_CERRADA_RECIBIR_PAGO = "No se puede realizar un pago a una cuenta cerrada";

    public static String BODY_NO_VALIDO = "Request body no valido";
    public static String REGISTRO_DUPLICADO = "Registro duplicado";
}
