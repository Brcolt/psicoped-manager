package br.com.coltextends.psicopediatria.repository;

import br.com.coltextends.psicopediatria.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
