package com.tecsup.petclinic.services;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;

/**
 * Implementación del servicio para manejar Owners
 */
@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Crea un nuevo Owner.
     *
     * @param owner
     * @return Owner creado
     */
    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     * Actualiza un Owner existente.
     *
     * @param owner
     * @return Owner actualizado
     */
    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     * Elimina un Owner por su ID.
     *
     * @param id
     * @throws OwnerNotFoundException si no se encuentra el Owner
     */
    @Override
    public void delete(Integer id) throws OwnerNotFoundException {
        Owner owner = findById(id);
        ownerRepository.delete(owner);
    }

    /**
     * Busca un Owner por su ID.
     *
     * @param id
     * @return Owner encontrado
     * @throws OwnerNotFoundException si no se encuentra el Owner
     */
    @Override
    public Owner findById(Integer id) throws OwnerNotFoundException {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (!owner.isPresent()) {
            throw new OwnerNotFoundException("Owner not found...!");
        }
        return owner.get();
    }

    /**
     * Busca Owners por apellido.
     *
     * @param lastName
     * @return Lista de Owners encontrados
     */
    @Override
    public List<Owner> findByLastName(String lastName) {
        List<Owner> owners = ownerRepository.findByLastName(lastName);
        owners.forEach(owner -> log.info("" + owner));
        return owners;
    }

    /**
     * Busca Owners por ciudad.
     *
     * @param city
     * @return Lista de Owners encontrados
     */
    @Override
    public List<Owner> findByCity(String city) {
        List<Owner> owners = ownerRepository.findByCity(city);
        owners.forEach(owner -> log.info("" + owner));
        return owners;
    }

    /**
     * Obtiene todos los Owners.
     *
     * @return Lista de todos los Owners
     */
    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }
}
