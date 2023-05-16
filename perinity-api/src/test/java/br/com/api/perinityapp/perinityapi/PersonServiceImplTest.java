package br.com.api.perinityapp.perinityapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.api.perinityapp.perinityapi.dto.DepartmentDTO;
import br.com.api.perinityapp.perinityapi.dto.PersonDTO;
import br.com.api.perinityapp.perinityapi.dto.PersonTaskDTO;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.model.TaskEntity;
import br.com.api.perinityapp.perinityapi.repository.PersonRepository;
import br.com.api.perinityapp.perinityapi.service.PersonService;
import br.com.api.perinityapp.perinityapi.service.PersonServiceImpl;
import br.com.api.perinityapp.perinityapi.service.TaskService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PersonServiceImplTest {

    private final PersonRepository personRepository = Mockito.mock(PersonRepository.class);
    private final PersonService personService = new PersonServiceImpl(personRepository);
    private final TaskService taskService = Mockito.mock(TaskService.class);

    @Test
    public void testDeletePerson() {
        // Criar uma instância simulada do repositório
        PersonRepository personRepository = mock(PersonRepository.class);

        // Criar uma instância do serviço e definir o repositório simulado
        PersonServiceImpl personService = new PersonServiceImpl(personRepository);

        // Criar uma pessoa para ser excluída
        PersonEntity person = new PersonEntity();
        person.setId(8L);
        person.setName("Rafael Assumpcao");
        person.setDepartment("Tecnologia");

        // Simular o comportamento do repositório ao chamar o método existsById
        when(personRepository.existsById(8L)).thenReturn(true); // Simular que a pessoa existe
        doNothing().when(personRepository).deleteById(4L); // Simular que a exclusão é realizada com sucesso

        // Chamar o método de exclusão
        personService.deletePerson(8L);

        // Verificar se o método existsById foi chamado corretamente no repositório
        verify(personRepository, times(1)).existsById(8L);

        // Verificar se o método deleteById foi chamado corretamente no repositório
        verify(personRepository, times(1)).deleteById(8L);
    }

    @Test
    public void testUpdatePerson() {
        Long personId = 1L;
        PersonEntity existingPerson = new PersonEntity();
        existingPerson.setId(personId);
        existingPerson.setName("John Doe");
        existingPerson.setDepartment("IT");

        PersonEntity updatedPerson = new PersonEntity();
        updatedPerson.setName("Jane Doe");
        updatedPerson.setDepartment("Finance");

        when(personRepository.findById(personId)).thenReturn(Optional.of(existingPerson));
        when(personRepository.save(any(PersonEntity.class))).thenReturn(updatedPerson);

        PersonEntity result = personService.updatePerson(personId, updatedPerson);

        assertNotNull(result);
        assertEquals(updatedPerson.getName(), result.getName());
        assertEquals(updatedPerson.getDepartment(), result.getDepartment());

        verify(personRepository, times(1)).findById(personId);
        verify(personRepository, times(1)).save(existingPerson);
    }

    @Test
    public void testGetAllPerson() {
        PersonEntity person1 = new PersonEntity();
        person1.setName("John Doe");
        person1.setDepartment("IT");

        PersonEntity person2 = new PersonEntity();
        person2.setName("Jane Smith");
        person2.setDepartment("Finance");

        List<PersonEntity> people = Arrays.asList(person1, person2);

        TaskEntity task1 = new TaskEntity();
        task1.setDuration(Duration.ofHours(2));

        TaskEntity task2 = new TaskEntity();
        task2.setDuration(Duration.ofHours(3));

        person1.setTasks(Arrays.asList(task1, task2));

        TaskEntity task3 = new TaskEntity();
        task3.setDuration(Duration.ofHours(4));

        person2.setTasks(Arrays.asList(task3)); // Configurar as tarefas para person2

        when(personRepository.findAll()).thenReturn(people);

        List<PersonTaskDTO> result = personService.getAllPerson();

        assertNotNull(result);
        assertEquals(2, result.size());

        PersonTaskDTO personTaskDTO1 = result.get(0);
        assertEquals(person1.getName(), personTaskDTO1.getPerson().getName());
        assertEquals(person1.getDepartment(), personTaskDTO1.getPerson().getDepartment());
        assertEquals(person1.getTasks(), personTaskDTO1.getTasks());

        PersonTaskDTO personTaskDTO2 = result.get(1);
        assertEquals(person2.getName(), personTaskDTO2.getPerson().getName());
        assertEquals(person2.getDepartment(), personTaskDTO2.getPerson().getDepartment());
        assertNotNull(personTaskDTO2.getTasks()); // Verificar se as tarefas não são nulas
        assertEquals(1, personTaskDTO2.getTasks().size()); // Verificar se há uma tarefa na lista

        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void testGetNameDepartmentAvgTask() {
        PersonEntity person1 = new PersonEntity();
        person1.setName("John Doe");
        person1.setDepartment("IT");

        TaskEntity task1 = new TaskEntity();
        task1.setDuration(Duration.ofHours(2));

        TaskEntity task2 = new TaskEntity();
        task2.setDuration(Duration.ofHours(3));

        person1.setTasks(Arrays.asList(task1, task2));

        List<PersonEntity> people = Arrays.asList(person1);

        when(personRepository.findAll()).thenReturn(people);

        List<PersonDTO> result = personService.getNameDepartmentAvgTask();

        assertNotNull(result);
        assertEquals(1, result.size());

        PersonDTO personDTO1 = result.get(0);
        assertEquals(person1.getName(), personDTO1.getName());
        assertEquals(person1.getDepartment(), personDTO1.getDepartment());

        double totalDuration1 = person1.getTasks()
                .stream()
                .mapToDouble(task -> task.getDuration().toHours())
                .sum();
        double avgTaskDuration1 = person1.getTasks().isEmpty() ? 0.0 : totalDuration1 / person1.getTasks().size();
        assertEquals(avgTaskDuration1, personDTO1.getAverageTaskDuration(), 0.01);

        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void testGetNameDepartmentTotalTaskDuration() {
        List<PersonEntity> people = new ArrayList<>();

        PersonEntity person1 = new PersonEntity();
        person1.setId(1L);
        person1.setName("John Doe");
        person1.setDepartment("IT");

        TaskEntity task1 = new TaskEntity();
        task1.setDuration(Duration.ofHours(2));

        people.add(person1);

        PersonEntity person2 = new PersonEntity();
        person2.setId(2L);
        person2.setName("Jane Smith");
        person2.setDepartment("Finance");

        TaskEntity task2 = new TaskEntity();
        task2.setDuration(Duration.ofHours(4));

        TaskEntity task3 = new TaskEntity();
        task3.setDuration(Duration.ofHours(6));

        people.add(person2);

        when(personRepository.findAll()).thenReturn(people);

        taskService.allocateTask(person1.getId(), person1);
        taskService.allocateTask(person2.getId(), person2);
        taskService.allocateTask(person2.getId(), person2);

        List<PersonDTO> result = personService.getNameDepartmentTotalTaskDuration();

        assertEquals(2, result.size());

        PersonDTO personDTO1 = result.get(0);
        assertEquals(person1.getName(), personDTO1.getName());
        assertEquals(person1.getDepartment(), personDTO1.getDepartment());
        assertEquals(2.0, personDTO1.getTotalTaskDuration(), 0.01);

        PersonDTO personDTO2 = result.get(1);
        assertEquals(person2.getName(), personDTO2.getName());
        assertEquals(person2.getDepartment(), personDTO2.getDepartment());
        assertEquals(10.0, personDTO2.getTotalTaskDuration(), 0.01);
    }

    @Test
    public void testGetDepartmentsWithCounts() {
        List<DepartmentDTO> departmentsWithCounts = new ArrayList<>();
        DepartmentDTO department1 = new DepartmentDTO();
        department1.setDepartmentName("IT");
        department1.setPersonCount(2L);
        departmentsWithCounts.add(department1);

        DepartmentDTO department2 = new DepartmentDTO();
        department2.setDepartmentName("Finance");
        department2.setTaskCount(3L);
        departmentsWithCounts.add(department2);

        when(personRepository.findDepartmentsWithCounts()).thenReturn(departmentsWithCounts);

        List<DepartmentDTO> result = personService.getDepartmentsWithCounts();

        assertEquals(2, result.size());
        assertEquals(department1.getDepartmentName(), result.get(0).getDepartmentName());
        assertEquals(department1.getPersonCount(), result.get(0).getPersonCount());
        assertEquals(department2.getDepartmentName(), result.get(1).getDepartmentName());
        assertEquals(department2.getTaskCount(), result.get(1).getTaskCount());
    }

}