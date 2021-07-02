package br.com.coltextends.psicopediatria.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {

    MALE("Male"),
    FEMALE("Female");

    public final String genders;
}
