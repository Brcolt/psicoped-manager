package br.com.coltextends.psicopediatria.repository;

import br.com.coltextends.psicopediatria.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByName(String name);

}
