package br.com.api.perinityapp.perinityapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.perinityapp.perinityapi.model.PersonEntity;
import br.com.api.perinityapp.perinityapi.model.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    @Query("SELECT t FROM TaskEntity t WHERE t.assignedPerson = :person")
    List<TaskEntity> findByAssignedPerson(PersonEntity person);

    List<TaskEntity> findTop3ByAssignedPersonIsNullOrderByDeadlineAsc();

}
