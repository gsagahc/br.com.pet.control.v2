package br.com.pet.control.services;

import br.com.pet.control.Application;
import br.com.pet.control.dto.PetCareCreateDTO;
import br.com.pet.control.dto.PetCareListDTO;
import br.com.pet.control.dto.PetCareResultDTO;
import br.com.pet.control.dto.PetOwnerDTO;
import br.com.pet.control.exceptions.ResourceNotFoundException;
import br.com.pet.control.model.PetCareEntity;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.model.PetOwnerEntity;
import br.com.pet.control.repository.PetCareRepository;
import br.com.pet.control.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PetCareServices {
	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
   
   @Autowired
   PetCareRepository petCareRepository;
   @Autowired
	PetRepository petRepository;
	final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	public PetCareEntity create(PetCareCreateDTO care) {
		PetEntity pet = petRepository.findById(care.petId())
						.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+care.petId()));
	     logger.info("Creating one Pet Care!" + " name:" +pet.getPetName()  );
		LocalDateTime dateTime = LocalDateTime.parse(care.dateTime(),formatter);
		PetCareEntity entity = new PetCareEntity();
		entity.setPet(pet);
		entity.setCareDate(dateTime);
		entity.setHasGrooming(care.hasGrooming());
	    return petCareRepository.save(entity);

    	
    }

	public PetCareEntity update(PetCareEntity care) {

		Optional<PetEntity> pet = Optional.ofNullable(petRepository.findById(care.getPet().
                getId()).orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:")));

		PetCareEntity entity = petCareRepository.findById(care.getId())
				.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+care.getId()));

		logger.info("Updating one service! id:"+care.getId()+" for this pet Name:"+ pet.get().getPetName());
		entity.setCareDate(care.getCareDate());
		entity.setHasGrooming(care.getHasGrooming());

		return petCareRepository.save(entity);

	}
	public void delete(Long id) {
		logger.info("Deleting one care, id:" + id);
		PetCareEntity entity = petCareRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:"+id));
		petCareRepository.delete(entity);

	}

	public PetCareResultDTO findByPetid(Long id) {
		logger.info("Finding one or more Pet cares by Pet id!"+id);
		Optional<PetEntity> pet = Optional.ofNullable(petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:" + id)));
	    PetCareResultDTO dto = createPetCareDtoList(pet);

		return dto;
	}

	public PetCareResultDTO createPetCareDtoList(Optional<PetEntity> pet){
	    List<PetCareEntity> petCareList = petCareRepository.findByPet(pet);
		List<PetCareListDTO> petCareListDTO = new ArrayList<>(List.of());
		for (PetCareEntity careList  : petCareList) {
			PetCareListDTO dto = new PetCareListDTO(
					careList.getId().toString(),
					careList.getCareDate().toString(),
					careList.getHasGrooming());
			petCareListDTO.add(dto);

		}
		PetCareResultDTO petCareResult =
				new PetCareResultDTO(pet,
					             	petCareListDTO);



		  return petCareResult;
	}



}
