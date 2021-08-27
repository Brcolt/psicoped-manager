package br.com.coltextends.psicopediatria.dto;

import br.com.coltextends.psicopediatria.model.Address;
import br.com.coltextends.psicopediatria.model.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TutorDTO {

    private Long id;

    @NotEmpty
    private Person person;

    @NotEmpty
    private List<Address> addresses;
}
