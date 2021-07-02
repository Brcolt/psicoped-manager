package br.com.coltextends.psicopediatria.DTO;


import br.com.coltextends.psicopediatria.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotEmpty
    private String name;

    private String cpf;

    private int age;

    @NotNull
    private GenderEnum gender;
}
