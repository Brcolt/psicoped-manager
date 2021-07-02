package br.com.coltextends.psicopediatria.repository;

import br.com.coltextends.psicopediatria.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
