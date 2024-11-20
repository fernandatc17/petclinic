package com.tecsup.petclinic.entities;

import lombok.Data;
import jakarta.persistence.*;

/**
 * Entidad que representa a los dueños de mascotas en el sistema.
 */
@Entity(name = "owners")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(length = 255)
    private String address;

    @Column(length = 80)
    private String city;

    @Column(length = 20)
    private String telephone;

    // Constructor vacío, requerido por JPA
    public Owner() {
    }

    // Constructor con todos los campos (excepto el ID, ya que se genera automáticamente)
    public Owner(String firstName, String lastName, String address, String city, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }
}
