//package com.scarnezis.desafio_devsu_microservicio_usuario.controller;
//
//import com.scarnezis.desafio_devsu_microservicio_usuario.entity.Persona;
//import com.scarnezis.desafio_devsu_microservicio_usuario.service.PersonaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/personas")
//public class PersonaController {
//
//    @Autowired
//    private PersonaService personaService;
//
//    @GetMapping
//    public List<Persona> getAllPersonas() {
//        return personaService.getAllPersonas();
//    }
//
//    @GetMapping("/{identificacion}")
//    public ResponseEntity<Persona> getPersonaById(@PathVariable String identificacion) {
//        Persona persona = personaService.getPersonaById(identificacion).orElseThrow(() -> new RuntimeException("Persona no encontrada con el ID: " + identificacion));
//        return ResponseEntity.ok(persona);
//    }
//
//    @PostMapping
//    public Persona createPersona(@RequestBody Persona persona) {
//        return personaService.createPersona(persona);
//    }
//
//    @PutMapping("/{identificacion}")
//    public ResponseEntity<Persona> updatePersona(@PathVariable String identificacion, @RequestBody Persona personaDetails) {
//        Persona updatedPersona = personaService.updatePersona(identificacion, personaDetails);
//        return ResponseEntity.ok(updatedPersona);
//    }
//
//    @DeleteMapping("/{identificacion}")
//    public ResponseEntity<Void> deletePersona(@PathVariable String identificacion) {
//        personaService.deletePersona(identificacion);
//        return ResponseEntity.noContent().build();
//    }
//}
