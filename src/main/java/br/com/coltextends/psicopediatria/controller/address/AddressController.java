package br.com.coltextends.psicopediatria.controller.address;


import br.com.coltextends.psicopediatria.dto.AddressDTO;
import br.com.coltextends.psicopediatria.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/v1/adresses")
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO create(@RequestBody AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }
}
