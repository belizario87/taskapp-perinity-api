package br.com.api.perinityapp.perinityapi.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.qos.logback.core.util.Duration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonDTO {

    private String name;
    private String department;
    private double averageTaskDuration;
    private double totalTaskDuration;

    public PersonDTO(String name, String department, Duration duration) {
    }

}
