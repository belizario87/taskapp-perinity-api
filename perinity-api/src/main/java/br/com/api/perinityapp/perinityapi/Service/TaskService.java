package br.com.api.perinityapp.perinityapi.Service;

import java.util.List;

import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.model.TaskEntity;

public interface TaskService {
    public TaskEntity allocateTask(Long id, PersonEntity person);

    public TaskEntity finishTask(Long id);

    // public List<TaskEntity> getPendingTasks();

    public TaskEntity addTask(TaskEntity task);
}
