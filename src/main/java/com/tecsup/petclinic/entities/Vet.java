package com.tecsup.petclinic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 *
 * @author jcoronel
 *
 */
@Entity
@Table(name = "vets")
@Data
@NoArgsConstructor
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    // Constructor completo
    public Vet(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Constructor sin el ID, en caso de crear una nueva instancia sin ID definido
    public Vet(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
