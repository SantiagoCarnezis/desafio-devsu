package com.scarnezis.desafio_devsu_microservicio_usuario.service;

import com.scarnezis.desafio_devsu_microservicio_usuario.entity.Persona;
import com.scarnezis.desafio_devsu_microservicio_usuario.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class PersonaService {

    private final PersonaRepository personaRepository;

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> getPersonaById(String identificacion) {
        return personaRepository.findById(identificacion);
    }

    public Persona createPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona updatePersona(String identificacion, Persona personaDetails) {
        Persona persona = personaRepository.findById(identificacion).orElseThrow(() -> new RuntimeException("Persona no encontrada con el ID: " + identificacion));
        persona.setNombre(personaDetails.getNombre());
        persona.setGenero(personaDetails.isGenero());
        persona.setEdad(personaDetails.getEdad());
        persona.setDireccion(personaDetails.getDireccion());
        persona.setTelefono(personaDetails.getTelefono());
        return personaRepository.save(persona);
    }

    public void deletePersona(String identificacion) {
        Persona persona = personaRepository.findById(identificacion).orElseThrow(() -> new RuntimeException("Persona no encontrada con el ID: " + identificacion));
        personaRepository.delete(persona);
    }
}
