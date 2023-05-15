package br.com.api.perinityapp.perinityapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import br.com.api.perinityapp.perinityapi.dto.PersonTaskDTO;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;

public interface PersonService {
    public PersonEntity addPerson(PersonEntity person);

    public PersonEntity updatePerson(Long id, PersonEntity person);

    public List<PersonTaskDTO> getAllPerson();

    public void deletePerson(Long id);

    public Map<String, Double> getPersonAvgTask(String name, LocalDate startDate, LocalDate endDate);
}
