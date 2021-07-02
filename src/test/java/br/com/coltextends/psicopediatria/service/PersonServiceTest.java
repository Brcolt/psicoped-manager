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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @InjectMocks
    private PersonService personService;

    @Test
    void whenAValidPersonIsInformedThemnItShouldBeCreated() throws EntityNotFoundException {

        //Given
        PersonDTO expectePersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedSavedPerson = personMapper.toModel(expectePersonDTO);

        //when
        when(personRepository.save(expectedSavedPerson)).thenReturn(expectedSavedPerson);

        //then
        PersonDTO createdPersonDTO = personService.createPerson(expectePersonDTO);
        assertThat(createdPersonDTO.getId(), is(equalTo(expectePersonDTO.getId())));
        verify(personRepository, times(1)).save(expectedSavedPerson);

    }
}
