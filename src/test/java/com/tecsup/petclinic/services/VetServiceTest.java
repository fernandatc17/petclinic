package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    /**
     * Prueba de busqueda de veterinario por ID
     */
    @Test
    public void testFindVetById() {

        Integer ID = 1;
        String FIRST_NAME = "James";
        String LAST_NAME = "Carter";
        Vet vet = null;

        try {
            vet = this.vetService.findById(ID);
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("" + vet);
        assertEquals(FIRST_NAME, vet.getFirstName());
        assertEquals(LAST_NAME, vet.getLastName());
    }

    /**
     * Pruebe la bssqueda de veterinarios por nombre.
     */
    @Test
    public void testFindVetByFirstName() {

        String FIND_FIRST_NAME = "Helen";
        int SIZE_EXPECTED = 1;

        List<Vet> vets = this.vetService.findByFirstName(FIND_FIRST_NAME);

        assertEquals(SIZE_EXPECTED, vets.size());
    }

    /**
     * Test de creacion de un nuevo veterinario
     */
    @Test
    public void testCreateVet() {

        String FIRST_NAME = "James";
        String LAST_NAME = "Watson";

        // Crear un nuevo Vet con first_name y last_name
        Vet vet = new Vet(FIRST_NAME, LAST_NAME);

        Vet vetCreated = this.vetService.create(vet);

        log.info("VET CREATED: " + vetCreated);

        assertNotNull(vetCreated.getId());
        assertEquals(FIRST_NAME, vetCreated.getFirstName());
        assertEquals(LAST_NAME, vetCreated.getLastName());
    }

    /**
     * Test updating a un veterinario existente
     */
    @Test
    public void testUpdateVet() {

        String FIRST_NAME = "Gregory";
        String LAST_NAME = "House";

        String UP_FIRST_NAME = "Greg";
        String UP_LAST_NAME = "House Updated";

        // Crear un nuevo Vet
        Vet vet = new Vet(FIRST_NAME, LAST_NAME);

        // Create vet
        log.info(">" + vet);
        Vet vetCreated = this.vetService.create(vet);
        log.info(">>" + vetCreated);

        // Update vet
        vetCreated.setFirstName(UP_FIRST_NAME);
        vetCreated.setLastName(UP_LAST_NAME);

        Vet upgradedVet = this.vetService.update(vetCreated);
        log.info(">>>>" + upgradedVet);

        assertEquals(UP_FIRST_NAME, upgradedVet.getFirstName());
        assertEquals(UP_LAST_NAME, upgradedVet.getLastName());
    }

    /**
     * Test deleting a vet.
     */
    @Test
    public void testDeleteVet() {

        String FIRST_NAME = "Stephen";
        String LAST_NAME = "Strange";

        // Crear un nuevo Vet
        Vet vet = new Vet(FIRST_NAME, LAST_NAME);
        vet = this.vetService.create(vet);
        log.info("" + vet);

        // Delete vet
        try {
            this.vetService.delete(vet.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        // Validation
        try {
            this.vetService.findById(vet.getId());
            assertTrue(false);
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }
    }
}