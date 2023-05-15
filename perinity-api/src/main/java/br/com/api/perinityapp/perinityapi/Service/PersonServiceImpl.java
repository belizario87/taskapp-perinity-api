package br.com.api.perinityapp.perinityapi.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.api.perinityapp.perinityapi.dto.PersonTaskDTO;
import br.com.api.perinityapp.perinityapi.exception.PersonNotFoundException;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.repository.PersonRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public PersonEntity addPerson(PersonEntity person) {
        PersonEntity savedPerson = personRepository.save(person);
        return savedPerson;

    }

    @Override
    public PersonEntity updatePerson(Long id, PersonEntity person) {

        PersonEntity personToUpdate = personRepository.findById(id).orElseThrow();
        personToUpdate.setName(person.getName());
        personToUpdate.setDepartment(person.getDepartment());
        personToUpdate.setTasks(person.getTasks());
        return personToUpdate;

    }

    @Override
    public List<PersonTaskDTO> getAllPerson() {
        List<PersonEntity> people = personRepository.findAll();
        List<PersonTaskDTO> personTaskDTOs = new ArrayList<>();

        for (PersonEntity person : people) {
            person.getTasks().size();
            PersonTaskDTO personTaskDTO = new PersonTaskDTO();
            personTaskDTO.setPerson(person);
            personTaskDTO.setTasks(person.getTasks());
            personTaskDTOs.add(personTaskDTO);
        }

        return personTaskDTOs;
    }

    @Override
    public void deletePerson(Long id) {
        List<PersonEntity> existingPersonList = personRepository.findAll();
        if (existingPersonList.isEmpty()) {
            throw new PersonNotFoundException("Nao ha pessoas cadastradas para deletar");
        }
        personRepository.deleteById(id);
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
