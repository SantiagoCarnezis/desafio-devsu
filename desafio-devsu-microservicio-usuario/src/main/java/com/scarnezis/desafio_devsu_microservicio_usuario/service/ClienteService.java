package com.scarnezis.desafio_devsu_microservicio_usuario.service;

import com.scarnezis.desafio_devsu_microservicio_usuario.dto.ObtenerCliente;
import com.scarnezis.desafio_devsu_microservicio_usuario.entity.Cliente;
import com.scarnezis.desafio_devsu_microservicio_usuario.excepcion.ClienteExistenteException;
import com.scarnezis.desafio_devsu_microservicio_usuario.excepcion.ClienteNoEncontradoException;
import com.scarnezis.desafio_devsu_microservicio_usuario.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final KafkaTemplate<String, String> kafkaCierreCuenta;
    private final String cierreCuentaTopic = "cierre-cuenta-topic";


    public List<ObtenerCliente> getAllClientes() {
        return clienteRepository.findCliente();
    }

    public ObtenerCliente getClienteById(String id) {

        return clienteRepository.findCliente(id).orElseThrow(() -> new ClienteNoEncontradoException(id));
    }

    public void createCliente(Cliente cliente) {

        boolean existe = clienteRepository.existsById(cliente.getIdentificacion());

        if(existe)
            throw new ClienteExistenteException(cliente.getIdentificacion());

        clienteRepository.save(cliente);
    }

    public void updateCliente(String id, Cliente clienteDto) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNoEncontradoException(id));

        cliente.setNombre(clienteDto.getNombre());
        cliente.setGenero(clienteDto.isGenero());
        cliente.setEdad(clienteDto.getEdad());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setPassword(clienteDto.getPassword());
        cliente.setEstado(clienteDto.isEstado());

        clienteRepository.save(cliente);
    }

    public void deleteCliente(String id) {

        clienteRepository.deleteById(id);
    }

    public void cerrarCuenta(String numeroCuenta) {

        kafkaCierreCuenta.send(cierreCuentaTopic, numeroCuenta);
    }
}

