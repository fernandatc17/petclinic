package com.tecsup.petclinic.services;

import java.util.List;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

/**
 * Servicio para manejar las operaciones de negocio relacionadas con Owner.
 */
public interface OwnerService {

    /**
     * Crea un nuevo Owner.
     *
     * @param owner
     * @return Owner creado
     */
    Owner create(Owner owner);

    /**
     * Actualiza un Owner existente.
     *
     * @param owner
     * @return Owner actualizado
     */
    Owner update(Owner owner);

    /**
     * Elimina un Owner por su ID.
     *
     * @param id
     * @throws OwnerNotFoundException si no se encuentra el Owner
     */
    void delete(Integer id) throws OwnerNotFoundException;

    /**
     * Busca un Owner por su ID.
     *
     * @param id
     * @return Owner encontrado
     * @throws OwnerNotFoundException si no se encuentra el Owner
     */
    Owner findById(Integer id) throws OwnerNotFoundException;

    /**
     * Busca Owners por apellido.
     *
     * @param lastName
     * @return Lista de Owners encontrados
     */
    List<Owner> findByLastName(String lastName);

    /**
     * Busca Owners por ciudad.
     *
     * @param city
     * @return Lista de Owners encontrados
     */
    List<Owner> findByCity(String city);

    /**
     * Obtiene todos los Owners.
     *
     * @return Lista de todos los Owners
     */
    List<Owner> findAll();
}

