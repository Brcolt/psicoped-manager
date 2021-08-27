package br.com.coltextends.psicopediatria.model;


import br.com.coltextends.psicopediatria.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @CPF
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "age")
    private int age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
}
