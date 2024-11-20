package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;

import java.util.List;

/**
 * Service interface for Specialty entity
 */
public interface SpecialtyService {

    /**
     * Create a new specialty
     *
     * @param specialty
     * @return
     */
    Specialty create(Specialty specialty);

    /**
     * Update an existing specialty
     *
     * @param specialty
     * @return
     */
    Specialty update(Specialty specialty);

    /**
     * Delete a specialty by its ID
     *
     * @param id
     * @throws SpecialtyNotFoundException
     */
    void delete(Integer id) throws SpecialtyNotFoundException;

    /**
     * Find a specialty by its ID
     *
     * @param id
     * @return
     * @throws SpecialtyNotFoundException
     */
    Specialty findById(Integer id) throws SpecialtyNotFoundException;

    /**
     * Find specialties by name
     *
     * @param name
     * @return
     */
    List<Specialty> findByName(String name);

    /**
     * Find specialties by office
     *
     * @param office
     * @return
     */
    List<Specialty> findByOffice(String office);

    /**
     * Retrieve all specialties
     *
     * @return
     */
    List<Specialty> findAll();
}
