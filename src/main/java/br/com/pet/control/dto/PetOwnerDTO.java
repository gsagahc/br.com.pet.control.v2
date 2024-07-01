package br.com.pet.control.dto;

import br.com.pet.control.model.PetEntity;

import java.util.List;


public record PetOwnerDTO(Long id, String name, String cpf, String
		                  email, String gender, String phoneNumber, String address,
						  String city, String uf, List<PetEntity> pets) {

}
