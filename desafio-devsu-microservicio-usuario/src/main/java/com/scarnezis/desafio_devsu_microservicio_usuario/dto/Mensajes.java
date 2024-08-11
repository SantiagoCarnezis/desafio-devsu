package com.scarnezis.desafio_devsu_microservicio_usuario.dto;

public class Mensajes {

    // Respuestas Exitosas
    public static final String CLIENTES_ENCONTRADOS = "Clientes encontrados exitosamente";
    public static final String CLIENTE_ENCONTRADO = "Cliente encontrado exitosamente";
    public static final String CLIENTE_ENCONTRADO_SIN_CUENTAS = "Cliente sin cuentas encontrado exitosamente";
    public static final String CLIENTE_CREADO = "Clientes creado exitosamente";
    public static final String CLIENTE_ACTUALIZADO = "Clientes actualizado exitosamente";
    public static final String CLIENTE_ELIMINADO = "Clientes eliminado exitosamente";
    public static final String CUENTA_CERRADA = "Cuenta cerrada";

    // Errores
    public static final String CLIENTE_NO_ENCONTRADO = "No se encontro el cliente ";
    public static final String ERROR_CONEXION_MICROSERVICIO_CUENTAS = "Fallo en la conexion con el microservicio cuentas. No se pudieron recuperar las cuentas del cliente ";
    public static final String CUENTAS_NO_RECUPERAS = "No se pudieron recuperar las cuentas del cliente ";
    public static final String ERROR_AL_RECUPERAR_CUENTAS = "Hubo un error al parsear las cuentas del cliente ";


}
