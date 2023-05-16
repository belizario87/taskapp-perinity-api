package br.com.api.perinityapp.perinityapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.api.perinityapp.perinityapi.exception.DepartmentMismatchException;
import br.com.api.perinityapp.perinityapi.exception.TaskNotFoundException;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.model.TaskEntity;
import br.com.api.perinityapp.perinityapi.repository.PersonRepository;
import br.com.api.perinityapp.perinityapi.repository.TaskRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PersonRepository personRepository;

    @Override
    public TaskEntity allocateTask(Long id, PersonEntity person) {
        Optional<TaskEntity> taskById = taskRepository.findById(id);

        if (taskById.isEmpty()) {
            throw new TaskNotFoundException("Task not found");
        }

        TaskEntity task = taskById.get();

        // Verifica se a pessoa pertence ao mesmo departamento da tarefa
        if (!person.getDepartment().equals(task.getDepartment())) {
            throw new DepartmentMismatchException("Department mismatch");
        }

        task.setAssignedPerson(person);
        taskRepository.save(task);
        return task;
    }

    @Override
    public TaskEntity finishTask(Long id) {
        Optional<TaskEntity> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            throw new TaskNotFoundException("Task not found");

        }

        TaskEntity task = taskOptional.get();
        task.setFinished(true);
        taskRepository.save(task);
        return task;
    }

    @Override
    public TaskEntity addTask(TaskEntity task) {
        PersonEntity assignedPerson = task.getAssignedPerson();

        if (assignedPerson != null) {
            assignedPerson = personRepository.save(assignedPerson);

            task.setAssignedPerson(assignedPerson);
        }

        TaskEntity savedTask = taskRepository.save(task);
        return savedTask;
    }

    @Override
    public List<TaskEntity> getPendingTasks() {
        List<TaskEntity> pendingTasks = taskRepository

                .findTop3ByAssignedPersonIsNullOrderByDeadlineAsc();

        return pendingTasks;
    }

}
