package br.com.pet.control.services;

import br.com.pet.control.Application;
import br.com.pet.control.exceptions.ResourceNotFoundException;
import br.com.pet.control.model.PetCareEntity;
import br.com.pet.control.model.PetEntity;
import br.com.pet.control.repository.PetCareRepository;
import br.com.pet.control.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class PetCareServices {
	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
   
   @Autowired
   PetCareRepository petCareRepository;
   @Autowired
	PetRepository petRepository;
	private final Date now = new Date();

	public PetCareEntity create(PetCareEntity care) {
		PetEntity pet = petRepository.findById(care.getPet().getId())
						.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+care.getPet().getId()));
	     logger.info("Creating one Pet Care!" + " name:" +pet.getPetName()  );
		 care.setPet(pet);
		 care.setCareDate(now);
		 return petCareRepository.save(care);

    	
    }

	public PetCareEntity update(PetCareEntity care) {

		Optional<PetEntity> pet = Optional.ofNullable(petRepository.findById(care.getPet().
                getId()).orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:")));

		PetCareEntity entity = petCareRepository.findById(care.getId())
				.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+care.getId()));

		logger.info("Updating one service! id:"+care.getId()+" for this pet Name:"+ pet.get().getPetName());
		entity.setCareDate(now);
		entity.setHasGrooming(care.getHasGrooming());

		return petCareRepository.save(entity);

	}
	public void delete(Long id) {
		logger.info("Deleting one care, id:" + id);
		PetCareEntity entity = petCareRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:"+id));
		petCareRepository.delete(entity);

	}



}
