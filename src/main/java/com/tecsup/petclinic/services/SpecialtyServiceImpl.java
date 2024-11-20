package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.exception.SpecialtyNotFoundException;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    /**
     * @param specialty
     * @return
     */
    @Override
    public Specialty create(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    /**
     * @param specialty
     * @return
     */
    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    /**
     * @param id
     * @throws SpecialtyNotFoundException
     */
    @Override
    public void delete(Integer id) throws SpecialtyNotFoundException {
        Specialty specialty = findById(id);
        specialtyRepository.delete(specialty);
    }

    /**
     * @param id
     * @return
     * @throws SpecialtyNotFoundException
     */
    @Override
    public Specialty findById(Integer id) throws SpecialtyNotFoundException {
        Optional<Specialty> specialty = specialtyRepository.findById(id);
        if (!specialty.isPresent()) {
            throw new SpecialtyNotFoundException("Record not found...!");
        }
        return specialty.get();
    }

    /**
     * @param name
     * @return
     */
    @Override
    public List<Specialty> findByName(String name) {
        List<Specialty> specialties = specialtyRepository.findByName(name);
        specialties.forEach(specialty -> log.info("" + specialty));
        return specialties;
    }

    /**
     * @param office
     * @return
     */
    @Override
    public List<Specialty> findByOffice(String office) {
        List<Specialty> specialties = specialtyRepository.findByOffice(office);
        specialties.forEach(specialty -> log.info("" + specialty));
        return specialties;
    }

    /**
     * @return
     */
    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }
}
