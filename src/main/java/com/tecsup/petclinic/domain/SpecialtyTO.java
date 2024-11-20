package com.tecsup.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Transfer Object for Specialty
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpecialtyTO {

    private Integer id;

    private String name;

    private String office;

    private String hOpen;

    private String hClose;

}
