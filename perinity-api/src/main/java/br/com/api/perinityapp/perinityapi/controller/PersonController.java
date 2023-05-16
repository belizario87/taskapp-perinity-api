package br.com.api.perinityapp.perinityapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.perinityapp.perinityapi.dto.DepartmentDTO;
import br.com.api.perinityapp.perinityapi.dto.PersonDTO;
import br.com.api.perinityapp.perinityapi.dto.PersonTaskDTO;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.service.PersonService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/listartudo")
    public ResponseEntity<List<PersonTaskDTO>> getAllPerson() {
        List<PersonTaskDTO> personTaskDTOs = personService.getAllPerson();
        return ResponseEntity.ok().body(personTaskDTOs);
    }

    @PostMapping("")
    public ResponseEntity<PersonEntity> addPerson(@RequestBody PersonEntity person) {
        PersonEntity savedPerson = personService.addPerson(person);
        return ResponseEntity.status(201).body(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable String id,
            @RequestBody PersonEntity person) {
        PersonEntity updatedPerson = personService.updatePerson(Long.parseLong(id), person);
        return ResponseEntity.ok().body(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/gastos")
    public ResponseEntity<List<PersonDTO>> getPersonWithTaskDuration() {
        List<PersonDTO> people = personService.getNameDepartmentAvgTask(); // pega as pessoas com a duração média de
                                                                           // tarefas

        return ResponseEntity.ok(people);
    }

    @GetMapping("")
    public ResponseEntity<List<PersonDTO>> getPersonTotalHours() {
        List<PersonDTO> people = personService.getNameDepartmentTotalTaskDuration();
        return ResponseEntity.ok(people);
    }

    @GetMapping("/departamentos")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsWithCounts() {
        List<DepartmentDTO> people = personService.getDepartmentsWithCounts();
        return ResponseEntity.ok(people);
    }

}
