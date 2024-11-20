package com.tecsup.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VetTO {

    private Integer id;

    private String firstName;  // Nombre del veterinario

    private String lastName;   // Apellido del veterinario

    // Si no se requiere este campo, puedes dejarlo así o eliminarlo
    public String getSpecialty() {
        return null;
    }
}
