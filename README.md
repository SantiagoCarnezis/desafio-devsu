# Desafío Devsu

En este desafío, se desarrollaron dos microservicios según las especificaciones solicitadas. A continuación, se detallan las funcionalidades implementadas y algunas características adicionales:

## Funcionalidades Básicas

- **CRUD para Microservicios**: Se crearon los controladores necesarios para realizar operaciones básicas de Crear, Leer, Actualizar y Eliminar (CRUD) en los dos microservicios.
Algunas operaciones como la modificación y eliminación de movimientos históricos no fueron incluidas, ya que no se alineaban con el modelo de negocio. Realice consultas al respecto pero no tuve una respuesta

## Funcionalidades Adicionales

- **Cerrar Cuenta**: Implementada para cambiar el estado de una cuenta a "cerrada". Una cuenta cerrada no puede ser utilizada para realizar ni recibir pagos. Como el enunciado solicitaba que debia exitir
comunicacion asincrona, decidi implementar esta funcionalidad mediante kafka. Quizas en un caso real seria mejor hacerlo de manera sincrona
  
- **Realizar Pago**: Permite transferir dinero de una cuenta a otra, validando que ambas cuentas existan, estén abiertas y tengan saldo suficiente. Crea movimientos en ambas cuentas dentro de una transaccion

- **Obtener Datos de Cuenta**: Endpoint de ejemplo que muestra cómo implementar comunicación sincrónica entre microservicios utilizando FeignClient. Soporta distintas excepciones

## Pruebas

- **Tests Unitarios**: Utlizado para probar los distintos casos de uso al realizar un pago.
- **Tests de Integración**: Utilizado para testar los distintos casos de uso de los reportes.
- **Test de Karate**: Utilizado para probar la aplicación en un entorno Docker en ejecución. Cabe destacar que docker debe estar corriendo para ejecutar este test

## Dockerización

La aplicación está dockerizada utilizando Docker Compose, que incluye:

- **Microservicios**: Movimientos y Usuarios.
- **Bases de Datos**: Una para cada servicio.
- **Zookeeper y Kafka**: Para la gestión de mensajes y comunicación asincrónica.
- **Eureka**: Service registry para la gestión de servicios.
- **API Gateway**: Centraliza las solicitudes, aunque, a modo de prueba, también permite el acceso directo a los servicios.

## Archivos Adjuntos

- **Colección Postman**: Incluye ejemplos de solicitudes para probar los endpoints mas 2 enviroments para realizar prueba mediante el api gateway o consultando directamente al servicio. https://drive.google.com/drive/folders/19O9A0YNCQCmbz6uiX9HZO5hw1xyk0N-m?usp=sharing
- **Archivo SQL**: Incluido en el Docker Compose para facilitar la inicialización de la base de datos.

## Despliegue

Para levantar la aplicacion mediante docker, es necesario asegurarse que ninguna de los puertos que docker usa para exponer los servicios este ocupado (se los puede encontrar en el archivo dev-su.env)
Una vez asegurado que los puertos esten disponibles, situado en el directorio donde se encuentra el archivo docker-compose.yml, se debe correr el siguiente comando

```bash
docker-compose up -d
```
o
```bash
docker-compose up
```
Para cualquier pregunta o aclaración adicional, no duden en contactarme a mi email scarnezis@gmail.com
