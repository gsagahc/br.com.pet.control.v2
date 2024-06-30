package br.com.pet.control.services;

import br.com.pet.control.Application;
import br.com.pet.control.model.AddressEntity;
import br.com.pet.control.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AddressServices {
	private static final Logger logger 
    = LoggerFactory.getLogger(Application.class);
   
   @Autowired
   AddressRepository addressRepository;


    
   public Optional<AddressEntity> findByID(Long id) {
	    logger.info("Showing all pets!");
        Optional<AddressEntity> address=  addressRepository.findById(id);
		return address;

   }

}
