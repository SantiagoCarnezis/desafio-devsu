package com.scarnezis.desafio_devsu_microservicio_usuario.repository;

import com.scarnezis.desafio_devsu_microservicio_usuario.dto.ObtenerCliente;
import com.scarnezis.desafio_devsu_microservicio_usuario.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @Query("SELECT new com.scarnezis.desafio_devsu_microservicio_usuario.dto.ObtenerCliente(" +
            "c.identificacion, c.nombre, c.genero, c.edad, c.direccion, c.telefono, c.estado) FROM Cliente c")
    List<ObtenerCliente> findCliente();

    @Query("SELECT new com.scarnezis.desafio_devsu_microservicio_usuario.dto.ObtenerCliente(" +
            "c.identificacion, c.nombre, c.genero, c.edad, c.direccion, c.telefono, c.estado) " +
            "FROM Cliente c WHERE c.identificacion = :id")
    Optional<ObtenerCliente> findCliente(String id);
}
