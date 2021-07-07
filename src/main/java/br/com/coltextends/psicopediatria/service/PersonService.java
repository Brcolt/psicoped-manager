package br.com.coltextends.psicopediatria.service;


import br.com.coltextends.psicopediatria.DTO.PersonDTO;
import br.com.coltextends.psicopediatria.mappers.PersonMapper;
import br.com.coltextends.psicopediatria.model.Person;
import br.com.coltextends.psicopediatria.repository.PersonRepository;
import br.com.coltextends.psicopediatria.utils.PropertyUtils;
import com.sun.deploy.perf.DefaultPerfHelper;
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

    public PersonDTO findById(Long idPerson) {
        Person person = personRepository.findById(idPerson).orElseThrow(RuntimeException::new);
        return personMapper.toDTO(person);
    }

    public PersonDTO findByName(String name) {
        Person person = personRepository.findByName(name).orElseThrow(RuntimeException::new);
        return personMapper.toDTO(person);
    }

    public PersonDTO update(Long id, PersonDTO personDTO) {
        PersonDTO toUpdateDTO = findById(id);
        Person personEntity = personMapper.toModel(toUpdateDTO);

        BeanUtils.copyProperties(personDTO, personEntity, PropertyUtils.getNullPropertyNames(personDTO, (String[]) ignoredProps.toArray()));
        personRepository.save(personEntity);
        return personMapper.toDTO(personEntity);
    }

}
