package br.com.coltextends.psicopediatria.mappers;


import br.com.coltextends.psicopediatria.dto.PersonDTO;
import br.com.coltextends.psicopediatria.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
