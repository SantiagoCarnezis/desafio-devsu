//package com.scarnezis.desafio_devsu_microservicio_usuario.service;
//
//import com.scarnezis.desafio_devsu_microservicio_usuario.entity.Person;
//import com.scarnezis.desafio_devsu_microservicio_usuario.repository.PersonRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
//public class PersonService {
//
//    private final PersonRepository personRepository;
//
//    public List<Person> getAll() {
//        return personRepository.findAll();
//    }
//
//    public Optional<Person> getPersonById(String identificacion) {
//        return personRepository.findById(identificacion);
//    }
//
//    public Person createPerson(Person person) {
//        return personRepository.save(person);
//    }
//
//    public Person updatePerson(String identificacion, Person personDetails) {
//        Person person = personRepository.findById(identificacion).orElseThrow(() -> new RuntimeException("Persona no encontrada con el ID: " + identificacion));
//        person.setName(personDetails.getName());
//        person.setGender(personDetails.isGender());
//        person.setAge(personDetails.getAge());
//        person.setDirection(personDetails.getDirection());
//        person.setPhone(personDetails.getPhone());
//        return personRepository.save(person);
//    }
//
//    public void deletePerson(String identificacion) {
//        Person person = personRepository.findById(identificacion).orElseThrow(() -> new RuntimeException("Persona no encontrada con el ID: " + identificacion));
//        personRepository.delete(person);
//    }
//}
