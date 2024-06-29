package br.com.pet.control.services;

import br.com.pet.control.Application;
import br.com.pet.control.dto.PetDTO;
import br.com.pet.control.exceptions.ResourceNotFoundException;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.model.PetOwnerEntity;
import br.com.pet.control.repository.PetOwnerRepository;
import br.com.pet.control.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PetServices {
	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
   
   @Autowired
   PetRepository petRepository;
   @Autowired
   PetOwnerRepository petOwnerRepository;
   @Autowired
   PetOwnerServices petOwnerServices;

    
   public List<PetDTO> findAll() {
	    logger.info("Showing all pets!");
        List<PetDTO> dtoList= createPetDtoList(petRepository.findAll());
		return dtoList;

   }
  
	public PetEntity create(PetEntity pet) {
    	logger.info("Creating one pet!"+" name:"+pet.getPetName());
     	  return petRepository.save(pet);

    }
    public PetEntity update(PetEntity pet) {
    	logger.info("Updating one pet! id:"+pet.getId()+" Name:"+pet.getPetName());
    	PetEntity entity = petRepository.findById(pet.getId())
     			.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+pet.getId()));
    	entity.setPetName(pet.getPetName());
    	entity.setPetBreed(pet.getPetBreed());
    	entity.setPetKind(pet.getPetKind());
    	entity.setOwner(pet.getOwner());
      	entity.setGender(pet.getGender());
    	return petRepository.save(entity);
    	
    }
  
	public PetDTO findByid(Long id) {
    	logger.info("Finding one pet!"+id);
        PetDTO dto =createPetDto(petRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+id)));
		return dto;

    }
	public void delete(Long id) {
		logger.info("Deleting one pet, id:" + id);
		PetEntity entity = petRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:"+id));
		 petRepository.delete(entity);

	}
	public List<PetDTO> createPetDtoList(List<PetEntity> petlist){
	    List<PetDTO> dtoList = new ArrayList<>(List.of());
		for (PetEntity pet  : petlist) {
			PetOwnerEntity ownerEntity = petOwnerServices.findByid(pet.getOwner());
			PetDTO dto = new PetDTO(
					pet.getId(),
					pet.getPetName(),
					pet.getPetBreed(),
					pet.getPetKind(),
					pet.getGender(),
					pet.getOwner(),
					ownerEntity.getName(),
					ownerEntity.getCpf());
			dtoList.add(dto);
		}
		return dtoList;
	}
	public PetDTO createPetDto(PetEntity pet){
		PetOwnerEntity ownerEntity = petOwnerServices.findByid(pet.getOwner());
		PetDTO dto = new PetDTO(
		    		pet.getId(),
					pet.getPetName(),
					pet.getPetBreed(),
					pet.getPetKind(),
					pet.getGender(),
					pet.getOwner(),
					ownerEntity.getName(),
					ownerEntity.getCpf());


		return dto;
	}
}
