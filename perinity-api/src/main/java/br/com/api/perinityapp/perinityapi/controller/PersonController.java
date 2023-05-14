package br.com.api.perinityapp.perinityapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.perinityapp.perinityapi.exception.PersonNotFoundException;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.repository.PersonRepository;

@RestController
@RequestMapping("/pessoas")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAllPeople() {
        List<PersonEntity> people = personRepository.findAll();
        if (people.isEmpty()) {
            throw new PersonNotFoundException("Nenhuma pessoa encontrada"); // valida se a lista está vazia
        }

        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity<PersonEntity> addPerson(@RequestBody PersonEntity person) {
        PersonEntity savedPerson = personRepository.save(person);
        return ResponseEntity.status(201).body(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> updatePerson(@PathVariable String id, @RequestBody PersonEntity updatedPerson) {
        Long parseId = Long.valueOf(id); // converte o id para long (o id é uma string e o id do banco é long)
        Optional<PersonEntity> existingPersonOptional = personRepository.findById(parseId);

        if (existingPersonOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PersonEntity existingPerson = existingPersonOptional.get();
        existingPerson.setName(updatedPerson.getName());
        existingPerson.setDepartment(updatedPerson.getDepartment());

        PersonEntity savedPerson = personRepository.save(existingPerson);
        return ResponseEntity.ok(savedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        List<PersonEntity> existingPersonList = personRepository.findAll();
        if (existingPersonList.isEmpty()) {
            throw new PersonNotFoundException("Nao ha pessoas cadastradas para deletar");
        }
        personRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping("/gastos")
    // public Map<String, Double> getPeopleAverageTaskCost(@RequestParam String
    // name, @RequestParam LocalDate startDate,
    // @RequestParam LocalDate endDate) {
    // List<PersonEntity> people =
    // personRepository.findByNameContainingAndTasksDeadlineBetween(name, startDate,
    // endDate);
    // Map<String, Double> averageTaskCosts = new HashMap<>();

    // for (PersonEntity person : people) {
    // double totalDuration = person.getTasks().stream()
    // .mapToDouble(task -> task.getDuration().toHours())
    // .sum();
    // double averageTaskCost = totalDuration / person.getTasks().size();
    // averageTaskCosts.put(person.getName(), averageTaskCost);
    // }

    // return averageTaskCosts;
    // }
}
