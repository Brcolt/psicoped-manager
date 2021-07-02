package br.com.coltextends.psicopediatria.builder;


import br.com.coltextends.psicopediatria.DTO.PersonDTO;
import br.com.coltextends.psicopediatria.enums.GenderEnum;
import lombok.Builder;

@Builder
public class PersonDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String name = "Bruno Colt";

    @Builder.Default
    private final String cpf = "085.785.114-41";

    @Builder.Default
    private final Integer age = 31;

    @Builder.Default
    private final GenderEnum gender = GenderEnum.MALE;

    public PersonDTO toPersonDTO() {
        return new PersonDTO(id, name, cpf, age, gender);
    }

}
