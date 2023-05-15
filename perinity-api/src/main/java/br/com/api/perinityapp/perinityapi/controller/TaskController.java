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

import br.com.api.perinityapp.perinityapi.Service.PersonService;
import br.com.api.perinityapp.perinityapi.Service.TaskService;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.model.TaskEntity;
import br.com.api.perinityapp.perinityapi.repository.PersonRepository;
import br.com.api.perinityapp.perinityapi.repository.TaskRepository;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@RestController
@RequestMapping("/tarefas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private PersonService personService;

    @PutMapping("/alocar/{id}")
    public ResponseEntity<TaskEntity> allocateTask(@PathVariable Long id,
            @RequestBody PersonEntity person) {

        TaskEntity task = taskService.allocateTask(id, person);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity<TaskEntity> finishTask(@PathVariable Long id) {

        TaskEntity finishedTask = taskService.finishTask(id);
        return ResponseEntity.ok(finishedTask);

    }

    // @GetMapping("/pendentes")
    // public List<TaskEntity> getPendingTasks() {
    // return taskRepository.findTop3ByAssignedPersonIsNullOrderByDeadlineAsc();
    // }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody TaskEntity task) {
        TaskEntity savedTask = taskService.addTask(task);
        return ResponseEntity.ok(savedTask);
    }
}
