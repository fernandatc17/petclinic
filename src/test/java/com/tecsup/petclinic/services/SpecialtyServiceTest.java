package com.tecsup.petclinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

    @Autowired
    private SpecialtyService specialtyService;

    /**
     * Test find specialty by ID
     */
    @Test
    public void testFindSpecialtyById() {

        Integer ID = 1;
        String NAME = "radiology";
        Specialty specialty = null;

        try {
            specialty = this.specialtyService.findById(ID);
        } catch (SpecialtyNotFoundException e) {
            fail(e.getMessage());
        }

        log.info("" + specialty);
        assertEquals(NAME, specialty.getName());
    }

    /**
     * Test find specialty by name
     */
    @Test
    public void testFindSpecialtyByName() {

        String FIND_NAME = "radiology";
        int SIZE_EXPECTED = 1;

        List<Specialty> specialties = this.specialtyService.findByName(FIND_NAME);

        assertEquals(SIZE_EXPECTED, specialties.size());
    }

    /**
     * Test create specialty
     */
    @Test
    public void testCreateSpecialty() {

        String SPECIALTY_NAME = "Dermatology";
        String OFFICE = "Farewall";
        String HOPEN = "9";
        String HCLOSE = "17";

        Specialty specialty = new Specialty(SPECIALTY_NAME, OFFICE, HOPEN, HCLOSE);

        Specialty specialtyCreated = this.specialtyService.create(specialty);

        log.info("SPECIALTY CREATED :" + specialtyCreated);

        assertNotNull(specialtyCreated.getId());
        assertEquals(SPECIALTY_NAME, specialtyCreated.getName());
        assertEquals(OFFICE, specialtyCreated.getOffice());
        assertEquals(HOPEN, specialtyCreated.getHOpen());
        assertEquals(HCLOSE, specialtyCreated.getHClose());
    }

    /**
     * Test update specialty
     */
    @Test
    public void testUpdateSpecialty() {

        String SPECIALTY_NAME = "surgery";
        String OFFICE = "Maryland";
        String HOPEN = "8";
        String HCLOSE = "12";

        String UP_SPECIALTY_NAME = "orthopedics";
        String UP_OFFICE = "Philipinds";
        String UP_HOPEN = "10";
        String UP_HCLOSE = "18";

        Specialty specialty = new Specialty(SPECIALTY_NAME, OFFICE, HOPEN, HCLOSE);

        // Create Specialty
        log.info(">" + specialty);
        Specialty specialtyCreated = this.specialtyService.create(specialty);
        log.info(">>" + specialtyCreated);

        // Update Specialty
        specialtyCreated.setName(UP_SPECIALTY_NAME);
        specialtyCreated.setOffice(UP_OFFICE);
        specialtyCreated.setHOpen(UP_HOPEN);
        specialtyCreated.setHClose(UP_HCLOSE);

        Specialty updatedSpecialty = this.specialtyService.update(specialtyCreated);
        log.info(">>>>" + updatedSpecialty);

        assertEquals(UP_SPECIALTY_NAME, updatedSpecialty.getName());
        assertEquals(UP_OFFICE, updatedSpecialty.getOffice());
        assertEquals(UP_HOPEN, updatedSpecialty.getHOpen());
        assertEquals(UP_HCLOSE, updatedSpecialty.getHClose());
    }

    /**
     * Test delete specialty
     */
    @Test
    public void testDeleteSpecialty() {

        String SPECIALTY_NAME = "dentistry";
        String OFFICE = "Terranova";
        String HOPEN = "9";
        String HCLOSE = "19";

        // Create Specialty
        Specialty specialty = new Specialty(SPECIALTY_NAME, OFFICE, HOPEN, HCLOSE);
        specialty = this.specialtyService.create(specialty);
        log.info("" + specialty);

        // Delete Specialty
        try {
            this.specialtyService.delete(specialty.getId());
        } catch (SpecialtyNotFoundException e) {
            fail(e.getMessage());
        }

        // Validation
        try {
            this.specialtyService.findById(specialty.getId());
            assertTrue(false);
        } catch (SpecialtyNotFoundException e) {
            assertTrue(true);
        }
    }
}
