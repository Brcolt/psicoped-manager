package br.com.coltextends.psicopediatria.controller;


import br.com.coltextends.psicopediatria.DTO.PersonDTO;
import br.com.coltextends.psicopediatria.builder.PersonDTOBuilder;
import br.com.coltextends.psicopediatria.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static br.com.coltextends.psicopediatria.utils.JsonConvertUtils.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    private static final String API_URL_PATH = "/api/v1/persons";

    private static final Long VALID_ID = 1L;

    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledWithAValidPersonThenAPersonIsCreated() throws Exception {

        //Given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.create(personDTO)).thenReturn(personDTO);

        //then
        mockMvc.perform(post(API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(personDTO.getName())));
    }

    @Test
    void whenGETIsCalledWithNoParametersThenAOkStatusIsReturned() throws Exception {

        //Given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.listAll()).thenReturn(Collections.singletonList(personDTO));

        //then
        mockMvc.perform(get(API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {

        //Given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.findById(personDTO.getId())).thenReturn(personDTO);

        //then

        mockMvc.perform(get(API_URL_PATH + "/" + VALID_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithValidNameThenOkStatusIsReturned() throws Exception {

        //Given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.findByName(personDTO.getName())).thenReturn(personDTO);

        //then

        mockMvc.perform(get(API_URL_PATH + "/name" + "?name=" + personDTO.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void whenPOSTIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {

        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        PersonDTO editedPersonDTO = personDTO;

        editedPersonDTO.setName("Bruno Colt Ferreira do Nascimento");

        //when
        when(personService.update(VALID_ID.longValue(), editedPersonDTO)).thenReturn(editedPersonDTO);

        mockMvc.perform(post(API_URL_PATH + "/edit/" + VALID_ID.longValue())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(editedPersonDTO)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.name", is(equalTo(personDTO.getName()))));

    }
}
