package br.com.coltextends.psicopediatria.mappers;

import br.com.coltextends.psicopediatria.dto.AddressDTO;
import br.com.coltextends.psicopediatria.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    public Address toModel(AddressDTO addressDTO);

    public AddressDTO toDTO(Address address);

}
