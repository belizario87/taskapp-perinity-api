package br.com.api.perinityapp.perinityapi.Service;

import java.util.List;

import br.com.api.perinityapp.perinityapi.dto.PersonTaskDTO;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;

public interface PersonService {
    public PersonEntity addPerson(PersonEntity person);

    public PersonEntity updatePerson(Long id, PersonEntity person);

    public List<PersonTaskDTO> getAllPerson();

    public void deletePerson(Long id);

    // public Map<String, Double> getPeopleAverageTaskCost(String name, LocalDate
    // startDate, LocalDate endDate);
}
