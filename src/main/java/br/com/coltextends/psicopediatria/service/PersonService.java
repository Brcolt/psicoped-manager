package br.com.coltextends.psicopediatria.service;


import br.com.coltextends.psicopediatria.DTO.PersonDTO;
import br.com.coltextends.psicopediatria.mappers.PersonMapper;
import br.com.coltextends.psicopediatria.model.Person;
import br.com.coltextends.psicopediatria.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public PersonDTO create(PersonDTO personDTO) {
        Person person = personRepository.save(personMapper.toModel(personDTO));
        return personMapper.toDTO(person);
    }
    
    public List<PersonDTO> listAll() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

}
