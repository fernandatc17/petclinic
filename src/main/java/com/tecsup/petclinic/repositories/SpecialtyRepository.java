package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Specialty;

/**
 * Repository for Specialty entity
 */
@Repository
public interface SpecialtyRepository
        extends JpaRepository<Specialty, Integer> {

    // Fetch specialties by name
    List<Specialty> findByName(String name);

    // Fetch specialties by office
    List<Specialty> findByOffice(String office);

    // Fetch all specialties
    @Override
    List<Specialty> findAll();
}

