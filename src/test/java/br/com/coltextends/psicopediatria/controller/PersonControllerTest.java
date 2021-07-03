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

import static org.hamcrest.core.Is.is;
import static br.com.coltextends.psicopediatria.utils.JsonConvertUtils.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    private static final String API_URL_PATH = "/api/v1/persons";

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
}
