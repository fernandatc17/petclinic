package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @Test
    public void testFindOwnerByName() {
        String FIND_LAST_NAME = "McTavish";
        int SIZE_EXPECTED = 1;
        List<Owner> owners = this.ownerService.findByLastName(FIND_LAST_NAME);
        assertEquals(SIZE_EXPECTED, owners.size());
    }

    @Test
    public void testCreateOwner() {
        String FIRST_NAME = "Carlos";
        String LAST_NAME = "Lopez";
        String ADDRESS = "123 Elm St.";
        String CITY = "Lima";
        String TELEPHONE = "123456789";

        Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
        Owner ownerCreated = this.ownerService.create(owner);
        log.info("OWNER CREATED: " + ownerCreated.toString());

        assertNotNull(ownerCreated.getId());
        assertEquals(FIRST_NAME, ownerCreated.getFirstName());
        assertEquals(LAST_NAME, ownerCreated.getLastName());
        assertEquals(ADDRESS, ownerCreated.getAddress());
        assertEquals(CITY, ownerCreated.getCity());
        assertEquals(TELEPHONE, ownerCreated.getTelephone());
    }

    @Test
    public void testUpdateOwner() {
        String FIRST_NAME = "Carlos";
        String LAST_NAME = "Lopez";
        String ADDRESS = "123 Elm St.";
        String CITY = "Lima";
        String TELEPHONE = "123456789";

        String UP_FIRST_NAME = "Carlos Updated";
        String UP_LAST_NAME = "Lopez Updated";
        String UP_ADDRESS = "456 Updated St.";
        String UP_CITY = "Cusco";
        String UP_TELEPHONE = "987654321";

        Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);

        // Create owner
        log.info(">" + owner);
        Owner ownerCreated = this.ownerService.create(owner);
        log.info(">>" + ownerCreated);

        // Update owner
        ownerCreated.setFirstName(UP_FIRST_NAME);
        ownerCreated.setLastName(UP_LAST_NAME);
        ownerCreated.setAddress(UP_ADDRESS);
        ownerCreated.setCity(UP_CITY);
        ownerCreated.setTelephone(UP_TELEPHONE);

        // Execute update
        Owner updatedOwner = this.ownerService.update(ownerCreated);
        log.info(">>>>" + updatedOwner);

        // Verify update
        assertEquals(UP_FIRST_NAME, updatedOwner.getFirstName());
        assertEquals(UP_LAST_NAME, updatedOwner.getLastName());
        assertEquals(UP_ADDRESS, updatedOwner.getAddress());
        assertEquals(UP_CITY, updatedOwner.getCity());
        assertEquals(UP_TELEPHONE, updatedOwner.getTelephone());
    }

    @Test
    public void testDeleteOwner() {
        String FIRST_NAME = "Carlos";
        String LAST_NAME = "Lopez";
        String ADDRESS = "123 Elm St.";
        String CITY = "Lima";
        String TELEPHONE = "123456789";

        // Create owner
        Owner owner = new Owner(FIRST_NAME, LAST_NAME, ADDRESS, CITY, TELEPHONE);
        owner = this.ownerService.create(owner);
        log.info("Owner created: " + owner);

        // Delete owner
        try {
            this.ownerService.delete(owner.getId());
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }

        // Validate deletion
        try {
            this.ownerService.findById(owner.getId());
            assertTrue(false);  // No debería encontrar al dueño eliminado
        } catch (OwnerNotFoundException e) {
            assertTrue(true);
        }
    }
}
