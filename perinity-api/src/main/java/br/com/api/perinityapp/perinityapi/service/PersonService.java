package br.com.api.perinityapp.perinityapi.service;

import java.util.List;

import br.com.api.perinityapp.perinityapi.dto.DepartmentDTO;
import br.com.api.perinityapp.perinityapi.dto.PersonDTO;
import br.com.api.perinityapp.perinityapi.dto.PersonTaskDTO;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;

public interface PersonService {
    public PersonEntity addPerson(PersonEntity person);

    public PersonEntity updatePerson(Long id, PersonEntity person);

    public List<PersonTaskDTO> getAllPerson();

    public void deletePerson(Long id);

    public List<PersonDTO> getNameDepartmentAvgTask();

    public List<PersonDTO> getNameDepartmentTotalTaskDuration();

    public List<DepartmentDTO> getDepartmentsWithCounts();

}
