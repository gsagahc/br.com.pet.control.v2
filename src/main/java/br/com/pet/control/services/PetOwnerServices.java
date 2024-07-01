package br.com.pet.control.services;

import br.com.pet.control.Application;
import br.com.pet.control.dto.PetDTO;
import br.com.pet.control.dto.PetOwnerDTO;
import br.com.pet.control.exceptions.ResourceNotFoundException;
import br.com.pet.control.model.AddressEntity;
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


@Service
public class PetOwnerServices {
	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
   
   @Autowired
   PetOwnerRepository petOwnerRepository;

    
   public List<PetOwnerDTO> findAll() {
	    logger.info("Showing all clients!");
	    List<PetOwnerDTO> dtoList= createPetOwnerDtoList(petOwnerRepository.findAll());
	    return dtoList;


   }
  
	public PetOwnerEntity create(PetOwnerEntity owner) {
    	logger.info("Creating one Pet Owner!"+" name:"+owner.getName());
	 	return petOwnerRepository.save(owner);
    	
    }
    public PetOwnerEntity update(PetOwnerEntity owner) {
    	logger.info("Updating one pet! id:"+owner.getId()+" Name:"+owner.getName());
    	PetOwnerEntity entity = petOwnerRepository.findById(owner.getId())
     			.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+owner.getId()));
    	entity.setName(owner.getName());
    	entity.setOwnerAddress(owner.getOwnerAddress());
    	entity.setGender(owner.getGender());
    	entity.setCpf(owner.getCpf());
    	entity.setPhone_number(owner.getPhone_number());
    	entity.setEmail(owner.getEmail());
    	return petOwnerRepository.save(entity);
    	
    }
  
	public PetOwnerDTO findByid(Long id) {
    	logger.info("Finding one Client!"+id);
		PetOwnerDTO dto = createPetOwnerDto(petOwnerRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+id)));
        return dto;
    }
	public void delete(Long id) {
		logger.info("Deleting one pet, id:" + id);
		PetOwnerEntity entity = petOwnerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:"+id));
		 petOwnerRepository.delete(entity);

	}
	public List<PetOwnerDTO> createPetOwnerDtoList(List<PetOwnerEntity> ownerList){
		List<PetOwnerDTO> dtoList = new ArrayList<>(List.of());

    	for (PetOwnerEntity owner  : ownerList) {
			PetOwnerDTO dto = new PetOwnerDTO(
					owner.getId(),
					owner.getName(),
					owner.getCpf(),
					owner.getEmail(),
					owner.getGender(),
					owner.getPhone_number(),
					owner.getOwnerAddress().getAddress(),
					owner.getOwnerAddress().getCity(),
					owner.getOwnerAddress().getUf(),
					owner.getPetList());
			dtoList.add(dto);
		}
		return dtoList;
	}
	public PetOwnerDTO createPetOwnerDto(PetOwnerEntity owner){
		PetOwnerDTO dto = new PetOwnerDTO(
					owner.getId(),
					owner.getName(),
					owner.getCpf(),
				    owner.getEmail(),
				    owner.getGender(),
					owner.getPhone_number(),
					owner.getOwnerAddress().getAddress(),
					owner.getOwnerAddress().getCity(),
					owner.getOwnerAddress().getUf(),
			    	owner.getPetList());


		return dto;
	}
}
