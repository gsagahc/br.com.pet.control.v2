package br.com.pet.control.dto;

import lombok.*;


public record PetOwnerDTO(Long id, String name, String cpf, String
		                  email, String gender, String phoneNumber, String address,
						  String city, String uf) {

}
