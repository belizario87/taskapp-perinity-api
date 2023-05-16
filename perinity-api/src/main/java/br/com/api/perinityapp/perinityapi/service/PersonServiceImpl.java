package br.com.api.perinityapp.perinityapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.api.perinityapp.perinityapi.dto.DepartmentDTO;
import br.com.api.perinityapp.perinityapi.dto.PersonDTO;
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
        PersonEntity personToUpdate = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Pessoa nao encontrada"));

        personToUpdate.setName(person.getName());
        personToUpdate.setDepartment(person.getDepartment());

        personRepository.save(personToUpdate);
        return personToUpdate;
    }

    @Override
    public List<PersonTaskDTO> getAllPerson() {
        List<PersonEntity> people = personRepository.findAll();
        List<PersonTaskDTO> personTaskDTOs = new ArrayList<>();

        for (PersonEntity person : people) {
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

    @Override
    public List<PersonDTO> getNameDepartmentAvgTask() {
        List<PersonEntity> people = personRepository.findAll();
        List<PersonDTO> personDTOs = new ArrayList<>();

        for (PersonEntity person : people) {
            PersonDTO personDTO = new PersonDTO();

            personDTO.setName(person.getName());
            personDTO.setDepartment(person.getDepartment());

            double totalDuration = person.getTasks()
                    .stream()
                    .mapToDouble(task -> task.getDuration().toHours())
                    .sum();

            double avgTaskDuration = totalDuration / person.getTasks().size();
            personDTO.setAverageTaskDuration(avgTaskDuration);

            personDTOs.add(personDTO);
        }

        return personDTOs;
    }

    public List<PersonDTO> getNameDepartmentTotalTaskDuration() {
        List<PersonEntity> people = personRepository.findAll();
        List<PersonDTO> personDTOs = new ArrayList<>();

        for (PersonEntity person : people) {

            double totalDuration = person.getTasks()
                    .stream()
                    .mapToDouble(task -> task.getDuration().toHours())
                    .sum();
            PersonDTO personDTO = new PersonDTO();
            personDTO.setName(person.getName());
            personDTO.setDepartment(person.getDepartment());
            personDTO.setTotalTaskDuration(totalDuration);

            personDTOs.add(personDTO);
        }

        return personDTOs;
    }

    @Override
    public List<DepartmentDTO> getDepartmentsWithCounts() {
        List<DepartmentDTO> departmentsWithCounts = personRepository.findDepartmentsWithCounts();
        return departmentsWithCounts;
    }

}
