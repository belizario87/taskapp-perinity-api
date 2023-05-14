package br.com.api.perinityapp.perinityapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.model.TaskEntity;
import br.com.api.perinityapp.perinityapi.repository.TaskRepository;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@RestController
@RequestMapping("/tarefas")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PutMapping("/alocar/{id}")
    public ResponseEntity<TaskEntity> allocateTask(@PathVariable Long id, @RequestBody PersonEntity person) {
        Optional<TaskEntity> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskEntity task = taskOptional.get();

        // Verifica se a pessoa pertence ao mesmo departamento da tarefa
        if (!person.getDepartment().equals(task.getDepartment())) {
            return ResponseEntity.badRequest().build();
        }

        task.setAssignedPerson(person.getName());
        taskRepository.save(task);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<TaskEntity> finishTask(@PathVariable Long id) {
        Optional<TaskEntity> taskOptional = taskRepository.findById(id);

        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TaskEntity task = taskOptional.get();
        task.setFinished(true);
        taskRepository.save(task);

        return ResponseEntity.ok(task);
    }

    // @GetMapping("/pendentes")
    // public List<TaskEntity> getPendingTasks() {
    // return taskRepository.findTop3ByAssignedPersonIsNullOrderByDeadlineAsc();
    // }

    @PostMapping("/")
    public ResponseEntity<TaskEntity> addTask(@RequestBody TaskEntity task) {
        return ResponseEntity.ok(taskRepository.save(task));
    }
}
