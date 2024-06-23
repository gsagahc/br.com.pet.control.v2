package br.com.pet.control.services;

import br.com.pet.control.Application;
import br.com.pet.control.exceptions.ResourceNotFoundException;
import br.com.pet.control.model.AddressEntity;
import br.com.pet.control.model.PetOwnerEntity;
import br.com.pet.control.repository.AddressRepository;
import br.com.pet.control.repository.PetOwnerRepository;
import br.com.pet.control.repository.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PetOwnerServices {
	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
   
   @Autowired
   PetOwnerRepository petOwnerRepository;

   @Autowired
	AddressRepository addressRepository;
    
   public List<PetOwnerEntity> findAll() {
	    logger.info("Showing all clients!");
		return petOwnerRepository.findAll();


   }
  
	public PetOwnerEntity create(PetOwnerEntity owner) {
    	logger.info("Creating one Pet Owner!"+" name:"+owner.getName());
		AddressEntity address = owner.getAddress();
		addressRepository.save(address);
    	return petOwnerRepository.save(owner);
    	
    }
    public PetOwnerEntity update(PetOwnerEntity owner) {
    	logger.info("Updating one pet! id:"+owner.getId()+" Name:"+owner.getName());
    	PetOwnerEntity entity = petOwnerRepository.findById(owner.getId())
     			.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+owner.getId()));
    	entity.setName(owner.getName());
    	entity.setAddress(owner.getAddress());
    	entity.setGender(owner.getGender());
    	entity.setCpf(owner.getCpf());
    	entity.setPhone_number(owner.getPhone_number());
    	entity.setEmail(owner.getEmail());
    	return petOwnerRepository.save(entity);
    	
    }
  
	public PetOwnerEntity findByid(Long id) {
    	logger.info("Finding one pet!"+id);

		return petOwnerRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+id));

    }
	public void delete(Long id) {
		logger.info("Deleting one pet, id:" + id);
		PetOwnerEntity entity = petOwnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:"+id));
		 petOwnerRepository.delete(entity);

	}
}
