package br.com.coltextends.psicopediatria.service;

import br.com.coltextends.psicopediatria.dto.AddressDTO;
import br.com.coltextends.psicopediatria.mappers.AddressMapper;
import br.com.coltextends.psicopediatria.model.Address;
import br.com.coltextends.psicopediatria.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    public AddressDTO create(AddressDTO addressDTO) {
        Address address = addressRepository.save(addressMapper.toModel(addressDTO));
        return addressMapper.toDTO(address);
    }
}
