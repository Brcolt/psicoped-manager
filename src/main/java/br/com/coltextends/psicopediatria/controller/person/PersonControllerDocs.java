package br.com.coltextends.psicopediatria.controller.person;


import br.com.coltextends.psicopediatria.dto.PersonDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api("PsyscoPed Manager")
public interface PersonControllerDocs {

    @ApiOperation(value = "Create a new person on database")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucess person creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field value")
    })
    PersonDTO create(PersonDTO personDTO);

    @ApiOperation(value = "Return all bears on database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "list of all registred persons")
    })
    List<PersonDTO> findAll();

    @ApiOperation("Find a person by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person finded!")
    })
    PersonDTO findById(@PathVariable Long id);

    @ApiOperation("Find a person by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Person finded!")
    })
    PersonDTO findByName(@PathVariable String name);

    @ApiOperation("Edit a person, finded by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Person updated")
    })
    PersonDTO update(@PathVariable Long id, @RequestBody PersonDTO personDTO);

    @ApiOperation("Delete a person, finded by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success person deleted"),
            @ApiResponse(code = 404, message = "Person not founded")
    })
    void delete(@PathVariable Long id);
}
