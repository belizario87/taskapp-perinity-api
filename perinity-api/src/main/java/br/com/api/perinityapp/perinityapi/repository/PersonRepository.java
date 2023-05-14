package br.com.api.perinityapp.perinityapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.perinityapp.perinityapi.model.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

}
