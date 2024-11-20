package com.tecsup.petclinic.mapper;

import com.tecsup.petclinic.domain.VetTO;
import com.tecsup.petclinic.entities.Vet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface VetMapper {

    // Mapea los campos de VetTO a Vet
    @Mapping(source = "firstName", target = "firstName")  // Mapeo correcto del nombre
    @Mapping(source = "lastName", target = "lastName")    // Mapeo correcto del apellido
    Vet toVet(VetTO vetTO);

    // Mapea los campos de Vet a VetTO
    @Mapping(source = "firstName", target = "firstName")  // Mapeo correcto del nombre
    @Mapping(source = "lastName", target = "lastName")    // Mapeo correcto del apellido
    VetTO toVetTO(Vet vet);

    // Mapeo de listas de Vet a VetTO y viceversa
    List<VetTO> toVetTOList(List<Vet> vetList);

    List<Vet> toVetList(List<VetTO> vetTOList);
}
