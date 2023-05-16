package br.com.api.perinityapp.perinityapi.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DepartmentDTO {

    private String departmentName;
    private Long personCount;
    private Long taskCount;

}
