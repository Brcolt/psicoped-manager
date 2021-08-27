package br.com.coltextends.psicopediatria.dto;


import br.com.coltextends.psicopediatria.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

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

    @CPF
    private String cpf;

    private int age;

    @NotNull
    private GenderEnum gender;
}
