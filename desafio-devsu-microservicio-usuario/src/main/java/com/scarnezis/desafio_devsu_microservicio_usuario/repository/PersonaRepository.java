package com.scarnezis.desafio_devsu_microservicio_usuario.repository;

import com.scarnezis.desafio_devsu_microservicio_usuario.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, String> {
}