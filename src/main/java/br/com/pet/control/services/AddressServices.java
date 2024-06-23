package br.com.pet.control.services;

import br.com.pet.control.Application;
import br.com.pet.control.exceptions.ResourceNotFoundException;
import br.com.pet.control.model.AddressEntity;
import br.com.pet.control.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServices {
	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
   
   @Autowired
   AddressRepository addressRepository;
    

	public AddressEntity create(AddressEntity address) {
    	logger.info("Creating one Owner Address!"+" name:"+address.getPetOwner().getName());
    	return addressRepository.save(address);
    	
    }
    public AddressEntity update(AddressEntity address) {
    	logger.info("Updating one pet! id:"+address.getId()+" Name:"+address.getPetOwner().());
		AddressEntity entity = addressRepository.findById(address.getId())
     			.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+address.getId()));
		entity.setPetOwner(address.getPetOwner());
    	entity.setAddress(address.getAddress());
    	entity.setCity(address.getCity());
    	entity.setUf(address.getUf());
    	return addressRepository.save(entity);
    	
    }
  
	public AddressEntity findByid(Long id) {
    	logger.info("Finding one Address!"+id);

		return addressRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Not records for ths id:"+id));

    }
	public void delete(Long id) {
		logger.info("Deleting one pet, id:" + id);
		AddressEntity entity = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not records for ths id:"+id));
		 addressRepository.delete(entity);

	}
}
