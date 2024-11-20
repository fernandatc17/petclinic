package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;

/**
 *
 * @author jcoronel
 *
 */
public interface VetService {

    /**
     * Creates a new Vet.
     *
     * @param vet the Vet entity to create
     * @return the created Vet
     */
    Vet create(Vet vet);

    /**
     * Updates an existing Vet.
     *
     * @param vet the Vet entity to update
     * @return the updated Vet
     */
    Vet update(Vet vet);

    /**
     * Deletes a Vet by ID.
     *
     * @param id the ID of the Vet to delete
     * @throws VetNotFoundException if the Vet is not found
     */
    void delete(Integer id) throws VetNotFoundException;

    /**
     * Finds a Vet by ID.
     *
     * @param id the ID of the Vet to find
     * @return the found Vet
     * @throws VetNotFoundException if the Vet is not found
     */
    Vet findById(Integer id) throws VetNotFoundException;

    /**
     * Finds Vets by first name.
     *
     * @param firstName the first name of the Vet(s) to find
     * @return a list of Vets with the specified first name
     */
    List<Vet> findByFirstName(String firstName);

    /**
     * Finds Vets by last name.
     *
     * @param lastName the last name of the Vet(s) to find
     * @return a list of Vets with the specified last name
     */
    List<Vet> findByLastName(String lastName);

    /**
     * Finds all Vets.
     *
     * @return a list of all Vets
     */
    List<Vet> findAll();
}
