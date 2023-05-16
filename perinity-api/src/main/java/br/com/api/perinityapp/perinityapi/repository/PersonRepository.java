package br.com.api.perinityapp.perinityapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.perinityapp.perinityapi.dto.DepartmentDTO;
import br.com.api.perinityapp.perinityapi.dto.PersonTaskDTO;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query("SELECT DISTINCT p FROM PersonEntity p JOIN FETCH p.tasks")
    List<PersonTaskDTO> findAllWithTasks();

    @Query("SELECT new br.com.api.perinityapp.perinityapi.dto.DepartmentDTO(p.department, COUNT(p), COUNT(t)) " +
            "FROM PersonEntity p " +
            "LEFT JOIN p.tasks t " +
            "GROUP BY p.department")
    List<DepartmentDTO> findDepartmentsWithCounts();

}
