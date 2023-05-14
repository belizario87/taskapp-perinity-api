package br.com.api.perinityapp.perinityapi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.perinityapp.perinityapi.model.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    // @Query("SELECT AVG(t.hoursSpent) FROM PersonEntity p JOIN p.tasks t WHERE
    // p.name = :name AND t.deadline BETWEEN :startDate AND :endDate")
    // List<PersonEntity>
    // findAverageHoursSpentByNameAndTaskDeadlineBetween(@Param("name") String name,
    // @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate
    // endDate);

    // List<PersonEntity> findByNameContainingAndTasksDeadlineBetween(String name,
    // LocalDate startDate, LocalDate endDate);

}
