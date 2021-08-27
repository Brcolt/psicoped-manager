package br.com.coltextends.psicopediatria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;

    private String cep;

    private String street;

    private Integer number;

    private String district;

    private String city;

    private String state;

    private String country;
}
