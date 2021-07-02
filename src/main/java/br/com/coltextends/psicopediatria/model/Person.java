package br.com.coltextends.psicopediatria.model;


import br.com.coltextends.psicopediatria.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String cpf;

    private int age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
}
