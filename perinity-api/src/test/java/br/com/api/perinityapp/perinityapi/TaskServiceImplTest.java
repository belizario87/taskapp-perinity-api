package br.com.api.perinityapp.perinityapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.api.perinityapp.perinityapi.exception.DepartmentMismatchException;
import br.com.api.perinityapp.perinityapi.exception.TaskNotFoundException;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.model.TaskEntity;
import br.com.api.perinityapp.perinityapi.repository.PersonRepository;
import br.com.api.perinityapp.perinityapi.repository.TaskRepository;
import br.com.api.perinityapp.perinityapi.service.TaskServiceImpl;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Captor
    private ArgumentCaptor<TaskEntity> taskCaptor;

    public TaskServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllocateTask_ValidTaskAndPerson_ReturnsAllocatedTask() {
        Long taskId = 1L;
        Long personId = 1L;
        String personName = "John Doe";
        String personDepartment = "IT";

        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setDepartment(personDepartment);

        PersonEntity person = new PersonEntity();
        person.setId(personId);
        person.setName(personName);
        person.setDepartment(personDepartment);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(personRepository.findById(personId)).thenReturn(Optional.of(person));
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(task);

        TaskEntity allocatedTask = taskService.allocateTask(taskId, person);

        assertEquals(person, allocatedTask.getAssignedPerson());
        verify(taskRepository).save(taskCaptor.capture());
        assertEquals(person, taskCaptor.getValue().getAssignedPerson());
    }

    @Test
    void testAllocateTask_TaskNotFound_ThrowsTaskNotFoundException() {
        Long taskId = 1L;
        Long personId = 1L;
        String personName = "John Doe";
        String personDepartment = "IT";

        PersonEntity person = new PersonEntity();
        person.setId(personId);
        person.setName(personName);
        person.setDepartment(personDepartment);

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.allocateTask(taskId, person));
        verify(taskRepository, never()).save(any(TaskEntity.class));
    }

    @Test
    void testAllocateTask_DepartmentMismatch_ThrowsDepartmentMismatchException() {
        Long taskId = 1L;
        Long personId = 1L;
        String personName = "John Doe";
        String personDepartment = "IT";
        String taskDepartment = "Finance";

        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setDepartment(taskDepartment);

        PersonEntity person = new PersonEntity();
        person.setId(personId);
        person.setName(personName);
        person.setDepartment(personDepartment);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        assertThrows(DepartmentMismatchException.class, () -> taskService.allocateTask(taskId, person));
        verify(taskRepository, never()).save(any(TaskEntity.class));
    }

    @Test
    void testFinishTask_ValidTask_ReturnsFinishedTask() {
        Long taskId = 1L;

        TaskEntity task = new TaskEntity();
        task.setId(taskId);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(task);

        TaskEntity finishedTask = taskService.finishTask(taskId);

        assertTrue(finishedTask.isFinished());
        verify(taskRepository).save(taskCaptor.capture());
        assertTrue(taskCaptor.getValue().isFinished());
    }

}
