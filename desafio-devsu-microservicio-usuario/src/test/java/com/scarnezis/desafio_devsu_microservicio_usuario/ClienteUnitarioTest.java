package com.scarnezis.desafio_devsu_microservicio_usuario;

import com.scarnezis.desafio_devsu_microservicio_usuario.controller.ClienteController;
import com.scarnezis.desafio_devsu_microservicio_usuario.dto.GenericResponse;
import com.scarnezis.desafio_devsu_microservicio_usuario.dto.Mensajes;
import com.scarnezis.desafio_devsu_microservicio_usuario.dto.ObtenerCliente;
import com.scarnezis.desafio_devsu_microservicio_usuario.excepcion.ClienteNoEncontradoException;
import com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente.Cuenta;
import com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente.CuentaClient;
import com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente.EstadoCuenta;
import com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente.TipoCuenta;
import com.scarnezis.desafio_devsu_microservicio_usuario.repository.ClienteRepository;
import com.scarnezis.desafio_devsu_microservicio_usuario.service.ClienteService;
import feign.FeignException;
import feign.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteUnitarioTest {

    @InjectMocks
    private ClienteController clienteController;
    private ClienteService clienteService;
    @Mock
    private CuentaClient cuentaClient;
    @Mock
    private ClienteRepository clienteRepository;
    private KafkaTemplate<String, String> kafkaCierreCuenta;
    private final String identificacionCliente = "123";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteService = new ClienteService(clienteRepository, kafkaCierreCuenta);
        clienteController = new ClienteController(clienteService, cuentaClient);
    }

    @Test
    public void testClienteConCuentas() throws Exception {

        List<Cuenta> cuentas = new ArrayList<>();
        Cuenta cuentaAhorro = new Cuenta("123456", identificacionCliente,
                TipoCuenta.AHORRO, 10.0, EstadoCuenta.ABIERTA);
        Cuenta cuentaCorriente = new Cuenta("654321", identificacionCliente,
                TipoCuenta.CORRIENTE, 10.0, EstadoCuenta.ABIERTA);
        cuentas.add(cuentaAhorro);
        cuentas.add(cuentaCorriente);

        ObtenerCliente clienteConCuentas = new ObtenerCliente(
                identificacionCliente, "santiago", true, 24,
                "direccion", "115323442", true, null);

        Mockito.when(clienteRepository.findCliente(identificacionCliente)).thenReturn(Optional.of(clienteConCuentas));

        Mockito.when(cuentaClient.getCuentas(identificacionCliente))
                .thenReturn(ResponseEntity.ok(GenericResponse.responseExistosa("Respuesta exitosa", cuentas)));

        GenericResponse response = clienteController.getClienteById(identificacionCliente);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getCodigo());
        Assertions.assertEquals(clienteConCuentas, response.getData());
        Assertions.assertFalse(((ObtenerCliente)response.getData()).getCuentas().isEmpty());
    }

    @Test
    public void testClienteSinCuentas() throws Exception {

        ObtenerCliente clienteSinCuentas = new ObtenerCliente(
                identificacionCliente, "santiago", true, 24,
                "direccion", "115323442", true, null);

        Mockito.when(clienteRepository.findCliente(identificacionCliente)).thenReturn(Optional.of(clienteSinCuentas));

        Mockito.when(cuentaClient.getCuentas(identificacionCliente))
                .thenReturn(ResponseEntity.ok(GenericResponse.responseExistosa("Respuesta exitosa", null)));

        GenericResponse response = clienteController.getClienteById(identificacionCliente);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getCodigo());
        Assertions.assertEquals(clienteSinCuentas, response.getData());
        Assertions.assertTrue(response.getMensaje().contains(Mensajes.CLIENTE_ENCONTRADO_SIN_CUENTAS));
        Assertions.assertNull(((ObtenerCliente)response.getData()).getCuentas());
    }

    @Test
    public void testErrorEnConexion() throws Exception {

        ObtenerCliente cliente = new ObtenerCliente(
                identificacionCliente, "santiago", true, 24,
                "direccion", "115323442", true, null);

        Mockito.when(clienteRepository.findCliente(identificacionCliente)).thenReturn(Optional.of(cliente));

        Request request = Request.create(Request.HttpMethod.GET, "url", Map.of(), new byte[0], Charset.defaultCharset(), null);

        Mockito.when(cuentaClient.getCuentas(identificacionCliente)).thenThrow(
                new FeignException.BadRequest("Error en la comunicacion con el microservicio de cuentas",
                        request, null, null));

        GenericResponse response = clienteController.getClienteById(identificacionCliente);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getCodigo());
        Assertions.assertEquals(cliente, response.getData());
        Assertions.assertTrue(response.getErrors().contains(Mensajes.CUENTAS_NO_RECUPERAS));
        Assertions.assertNull(((ObtenerCliente)response.getData()).getCuentas());
    }

    @Test
    public void testClienteInexistente() {

        Mockito.when(clienteRepository.findCliente(identificacionCliente))
                .thenThrow(new ClienteNoEncontradoException(identificacionCliente));

        ClienteNoEncontradoException exception = assertThrows(ClienteNoEncontradoException.class, () -> {
            clienteController.getClienteById(identificacionCliente);
        });

        Assertions.assertTrue(exception.getMessage().contains(Mensajes.CLIENTE_NO_ENCONTRADO + identificacionCliente));
    }
}

