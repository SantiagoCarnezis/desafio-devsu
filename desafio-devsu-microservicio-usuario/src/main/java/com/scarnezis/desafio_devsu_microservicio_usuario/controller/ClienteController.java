package com.scarnezis.desafio_devsu_microservicio_usuario.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scarnezis.desafio_devsu_microservicio_usuario.dto.GenericResponse;
import com.scarnezis.desafio_devsu_microservicio_usuario.dto.Mensajes;
import com.scarnezis.desafio_devsu_microservicio_usuario.dto.ObtenerCliente;
import com.scarnezis.desafio_devsu_microservicio_usuario.dto.ClienteConCuentas;
import com.scarnezis.desafio_devsu_microservicio_usuario.entity.Cliente;
import com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente.CuentaClient;
import com.scarnezis.desafio_devsu_microservicio_usuario.feignCliente.Cuenta;
import com.scarnezis.desafio_devsu_microservicio_usuario.service.ClienteService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ConnectException;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ClienteController {

    private final ClienteService clienteService;
    private final CuentaClient cuentaClient;
    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public GenericResponse getAllClientes() {

        List<ObtenerCliente> clientes = clienteService.getAllClientes();
        return GenericResponse.responseExistosa(Mensajes.CLIENTES_ENCONTRADOS, clientes);
    }

    @GetMapping("/{id}")
    public GenericResponse getClienteById(@PathVariable String id) {

        GenericResponse response = null;
        ObtenerCliente cliente = clienteService.getClienteById(id);

        try {

            ResponseEntity<GenericResponse> responseFeignClient = cuentaClient.getCuentas(id);

            if (responseFeignClient.getBody() != null){

                String cuentasString = mapper.writeValueAsString(responseFeignClient.getBody().getData());
                List<Cuenta> cuentas = mapper.readValue(cuentasString, new TypeReference<>() {});

                if (cuentas != null && !cuentas.isEmpty()) {
                    cliente.setCuentas(cuentas);
                    response = GenericResponse.responseExistosa(Mensajes.CLIENTE_ENCONTRADO, cliente);
                }
                else
                    response = GenericResponse.responseExistosa(Mensajes.CLIENTE_ENCONTRADO_SIN_CUENTAS, cliente);
            }
            else
                response = GenericResponse.responseExistosa(Mensajes.CLIENTE_ENCONTRADO_SIN_CUENTAS, cliente);
        }
        catch (ConnectException | FeignException ex){

            response = GenericResponse.responseExistosa(Mensajes.CLIENTE_ENCONTRADO, cliente);
            response.addError(Mensajes.CUENTAS_NO_RECUPERAS);
            logger.error(Mensajes.ERROR_CONEXION_MICROSERVICIO_CUENTAS + id);
        }
        catch (JsonProcessingException e) {
            response = GenericResponse.responseExistosa(Mensajes.CLIENTE_ENCONTRADO, cliente);
            response.addError(Mensajes.CUENTAS_NO_RECUPERAS);
            logger.error(Mensajes.ERROR_AL_RECUPERAR_CUENTAS + id);
        }

        return response;
    }

    @PostMapping
    public GenericResponse createCliente(@RequestBody Cliente clienteDto) {
        clienteService.createCliente(clienteDto);
        return GenericResponse.responseExistosa(Mensajes.CLIENTE_CREADO, null);
    }

    @PutMapping("/{id}")
    public GenericResponse updateCliente(@PathVariable String id, @RequestBody Cliente clienteDto) {
        clienteService.updateCliente(id, clienteDto);
        return GenericResponse.responseExistosa(Mensajes.CLIENTE_ACTUALIZADO, null);
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteCliente(@PathVariable String id) {
        clienteService.deleteCliente(id);
        return GenericResponse.responseExistosa(Mensajes.CLIENTE_ELIMINADO, null);
    }

    @PostMapping("/cerrar-cuenta/{numeroCuenta}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public GenericResponse cerrarCuenta(@PathVariable String numeroCuenta) {
        clienteService.cerrarCuenta(numeroCuenta);
        GenericResponse response = GenericResponse.responseExistosa(Mensajes.CUENTA_CERRADA, null);
        response.setCodigo(202);
        return response;
    }
}
