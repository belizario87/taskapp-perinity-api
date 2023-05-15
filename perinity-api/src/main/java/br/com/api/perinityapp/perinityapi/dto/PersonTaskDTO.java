package br.com.api.perinityapp.perinityapi.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.model.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonTaskDTO {
    private PersonEntity person;
    List<TaskEntity> tasks = new ArrayList<>();
}
