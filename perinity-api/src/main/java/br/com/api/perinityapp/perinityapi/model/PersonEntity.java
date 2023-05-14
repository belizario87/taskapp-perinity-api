package br.com.api.perinityapp.perinityapi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.config.Task;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String department;

    @OneToMany(mappedBy = "assignedPerson", cascade = CascadeType.ALL) // uma tarefa pode ter varias pessoas
    @JsonIgnore
    private List<TaskEntity> tasks = new ArrayList<>();

}
