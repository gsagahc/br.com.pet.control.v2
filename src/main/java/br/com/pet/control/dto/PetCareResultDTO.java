package br.com.pet.control.dto;

import br.com.pet.control.model.PetCareEntity;
import br.com.pet.control.model.PetEntity;

import java.util.List;
import java.util.Optional;

public record PetCareResultDTO(Optional<PetEntity> pet,
							   List<PetCareListDTO> petCareList) {


}
