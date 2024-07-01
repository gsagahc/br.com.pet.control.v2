package br.com.pet.control.services;

import java.util.ArrayList;
import java.util.List;

import br.com.pet.control.dto.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pet.control.model.PetEntity;



@Service
public class  MockPet {
	@Autowired
	PetServices petServices;
	public PetEntity mockEntity() {
        return mockEntity(0);
    }
	 public List<PetEntity> mockEntityList() {
	        List<PetEntity> pets = new ArrayList<PetEntity>();
	        for (int i = 0; i < 14; i++) {
	            pets.add(mockEntity(i));
	        }
	        return pets;
	    }
	 
	  public PetEntity mockEntity(Integer number) {
		    PetEntity entity = new PetEntity();
			entity.setPetName("Name Pet Test" + number);
		    entity.setPetBreed("Breed Test" + number);
			entity.setPetKind("Kind Test" + number);
			entity.setGender(((number % 2)==0) ? "Male" : "Female");
			entity.setId(number.longValue());
			entity.setOwner(27L);
		    return entity;
	    }
		

}
