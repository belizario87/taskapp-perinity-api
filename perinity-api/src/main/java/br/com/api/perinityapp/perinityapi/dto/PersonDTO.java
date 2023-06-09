package br.com.api.perinityapp.perinityapi.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonDTO {

    private String name;
    private String department;
    private double averageTaskDuration;
    private double totalTaskDuration;

}
