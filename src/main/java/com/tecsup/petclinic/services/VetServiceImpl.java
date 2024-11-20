package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import com.tecsup.petclinic.repositories.VetRepository;

/**
 *
 * @author jgcoronel
 *
 */
@Service
@Slf4j
public class VetServiceImpl implements VetService {

    VetRepository vetRepository;

    public VetServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    /**
     * Creates a new Vet.
     *
     * @param vet the Vet to create
     * @return the created Vet
     */
    @Override
    public Vet create(Vet vet) {
        return vetRepository.save(vet);
    }

    /**
     * Updates an existing Vet.
     *
     * @param vet the Vet to update
     * @return the updated Vet
     */
    @Override
    public Vet update(Vet vet) {
        return vetRepository.save(vet);
    }

    /**
     * Deletes a Vet by ID.
     *
     * @param id the ID of the Vet to delete
     * @throws VetNotFoundException if the Vet is not found
     */
    @Override
    public void delete(Integer id) throws VetNotFoundException {
        Vet vet = findById(id);
        vetRepository.delete(vet);
    }

    /**
     * Finds a Vet by ID.
     *
     * @param id the ID of the Vet to find
     * @return the found Vet
     * @throws VetNotFoundException if the Vet is not found
     */
    @Override
    public Vet findById(Integer id) throws VetNotFoundException {
        Optional<Vet> vet = vetRepository.findById(id);

        if (!vet.isPresent()) {
            throw new VetNotFoundException("Record not found...!");
        }

        return vet.get();
    }

    /**
     * Finds Vets by first name.
     *
     * @param firstName the first name of the Vet(s) to find
     * @return a list of Vets with the specified first name
     */
    @Override
    public List<Vet> findByFirstName(String firstName) {
        List<Vet> vets = vetRepository.findByFirstName(firstName);
        vets.forEach(vet -> log.info("" + vet));
        return vets;
    }

    /**
     * Finds Vets by last name.
     *
     * @param lastName the last name of the Vet(s) to find
     * @return a list of Vets with the specified last name
     */
    @Override
    public List<Vet> findByLastName(String lastName) {
        List<Vet> vets = vetRepository.findByLastName(lastName);
        vets.forEach(vet -> log.info("" + vet));
        return vets;
    }

    /**
     * Finds all Vets.
     *
     * @return a list of all Vets
     */
    @Override
    public List<Vet> findAll() {
        return vetRepository.findAll();
    }
}
