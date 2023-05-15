package br.com.api.perinityapp.perinityapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.perinityapp.perinityapi.dto.PersonTaskDTO;
import br.com.api.perinityapp.perinityapi.model.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonEntity> findByNameContainingAndTasksDeadlineBetween(String name, LocalDate startDate, LocalDate endDate);

    @Query("SELECT DISTINCT p FROM PersonEntity p JOIN FETCH p.tasks")
    List<PersonTaskDTO> findAllWithTasks();
}
