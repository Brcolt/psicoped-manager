package br.com.coltextends.psicopediatria.service;


import br.com.coltextends.psicopediatria.DTO.PersonDTO;
import br.com.coltextends.psicopediatria.mappers.PersonMapper;
import br.com.coltextends.psicopediatria.model.Person;
import br.com.coltextends.psicopediatria.repository.PersonRepository;
import br.com.coltextends.psicopediatria.utils.PropertyUtils;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    private List<String> ignoredProps = Arrays.asList("id");

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

    public PersonDTO findById(Long idPerson) throws NotFoundException {
        Person person = verifyIfExists(idPerson);
        return personMapper.toDTO(person);
    }

    public PersonDTO findByName(String name) {
        Person person = personRepository.findByName(name).orElseThrow(RuntimeException::new);
        return personMapper.toDTO(person);
    }

    public PersonDTO update(Long id, PersonDTO personDTO) throws NotFoundException {
        Person toUpdate = verifyIfExists(id);

        BeanUtils.copyProperties(personDTO, toUpdate, PropertyUtils.getNullPropertyNames(personDTO, (String[]) ignoredProps.toArray()));
        personRepository.save(toUpdate);
        return personMapper.toDTO(toUpdate);
    }

    public void deleteById(Long id) throws NotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws NotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

}
