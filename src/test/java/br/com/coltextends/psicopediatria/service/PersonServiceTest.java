package br.com.coltextends.psicopediatria.service;


import br.com.coltextends.psicopediatria.DTO.PersonDTO;
import br.com.coltextends.psicopediatria.builder.PersonDTOBuilder;
import br.com.coltextends.psicopediatria.mappers.PersonMapper;
import br.com.coltextends.psicopediatria.model.Person;
import br.com.coltextends.psicopediatria.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    private final static Long VALID_ID = 1L;

    @Mock
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @InjectMocks
    private PersonService personService;

    @Test
    void whenAValidPersonIsInformedThemnItShouldBeCreated() throws EntityNotFoundException {

        //Given
        PersonDTO expectedPersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedSavedPerson = personMapper.toModel(expectedPersonDTO);

        //when
        when(personRepository.save(expectedSavedPerson)).thenReturn(expectedSavedPerson);

        //then
        PersonDTO createdPersonDTO = personService.create(expectedPersonDTO);
        assertThat(createdPersonDTO.getId(), is(equalTo(expectedPersonDTO.getId())));
        verify(personRepository, times(1)).save(expectedSavedPerson);

    }

    @Test
    void whenPersonIsSearchThenItShouldBeShowed() throws EntityNotFoundException {

        //Given
        PersonDTO expectedPersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedFoundPerson = personMapper.toModel(expectedPersonDTO);


        //when
        when(personRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPerson));

        //Then
        List<PersonDTO> foundedPersonsDTO = personService.listAll();
        verify(personRepository, times(1)).findAll();
        assertThat(foundedPersonsDTO, is(not(empty())));
        assertThat(foundedPersonsDTO.get(0), equalTo(expectedPersonDTO));
    }

    @Test
    void whenPersonWithAValidIdIsSearchedThenItShouldBeShowed() throws EntityNotFoundException {

        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person person = personMapper.toModel(personDTO);

        //when
        when(personRepository.findById(personDTO.getId())).thenReturn(Optional.of(person));

        //then
        PersonDTO foundedPersonDTO = personService.findById(personDTO.getId());

        verify(personRepository, times(1)).findById(VALID_ID);
        assertThat(foundedPersonDTO.getId(), is(equalTo(personDTO.getId())));
    }
}
