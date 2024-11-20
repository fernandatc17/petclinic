package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.mapper.OwnerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecsup.petclinic.domain.OwnerTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.services.OwnerService;

import java.util.List;

/**
 * Controlador para la entidad Owner.
 *
 */
@RestController
@Slf4j
public class OwnerController {

    private OwnerService ownerService;
    private OwnerMapper mapper;

    /**
     * Constructor para inyectar servicios.
     *
     * @param ownerService
     * @param mapper
     */
    public OwnerController(OwnerService ownerService, OwnerMapper mapper) {
        this.ownerService = ownerService;
        this.mapper = mapper;
    }

    /**
     * Obtener todos los dueños.
     *
     * @return Lista de OwnerTO
     */
    @GetMapping(value = "/owners")
    public ResponseEntity<List<OwnerTO>> findAllOwners() {
        List<Owner> owners = ownerService.findAll();
        log.info("owners: " + owners);
        owners.forEach(item -> log.info("Owner >>  {} ", item));

        List<OwnerTO> ownersTO = this.mapper.toOwnerTOList(owners);
        log.info("ownersTO: " + ownersTO);
        ownersTO.forEach(item -> log.info("OwnerTO >>  {} ", item));

        return ResponseEntity.ok(ownersTO);
    }

    /**
     * Crear un nuevo dueño.
     *
     * @param ownerTO
     * @return OwnerTO creado
     */
    @PostMapping(value = "/owners")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<OwnerTO> create(@RequestBody OwnerTO ownerTO) {
        Owner newOwner = this.mapper.toOwner(ownerTO);
        OwnerTO newOwnerTO = this.mapper.toOwnerTO(ownerService.create(newOwner));
        return ResponseEntity.status(HttpStatus.CREATED).body(newOwnerTO);
    }

    /**
     * Encontrar un dueño por ID.
     *
     * @param id
     * @return OwnerTO encontrado
     * @throws OwnerNotFoundException
     */
    @GetMapping(value = "/owners/{id}")
    ResponseEntity<OwnerTO> findById(@PathVariable Integer id) {
        OwnerTO ownerTO = null;
        try {
            Owner owner = ownerService.findById(id);
            ownerTO = this.mapper.toOwnerTO(owner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ownerTO);
    }

    /**
     * Actualizar un dueño existente.
     *
     * @param ownerTO
     * @param id
     * @return OwnerTO actualizado
     */
    @PutMapping(value = "/owners/{id}")
    ResponseEntity<OwnerTO> update(@RequestBody OwnerTO ownerTO, @PathVariable Integer id) {
        OwnerTO updatedOwnerTO = null;
        try {
            Owner updatedOwner = ownerService.findById(id);
            updatedOwner.setFirstName(ownerTO.getFirstName());
            updatedOwner.setLastName(ownerTO.getLastName());
            updatedOwner.setAddress(ownerTO.getAddress());
            updatedOwner.setCity(ownerTO.getCity());
            updatedOwner.setTelephone(ownerTO.getTelephone());

            ownerService.update(updatedOwner);
            updatedOwnerTO = this.mapper.toOwnerTO(updatedOwner);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedOwnerTO);
    }

    /**
     * Eliminar un dueño por ID.
     *
     * @param id
     * @return Mensaje de éxito
     */
    @DeleteMapping(value = "/owners/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            ownerService.delete(id);
            return ResponseEntity.ok("Delete ID: " + id);
        } catch (OwnerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
