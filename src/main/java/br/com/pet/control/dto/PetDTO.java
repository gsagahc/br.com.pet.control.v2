package br.com.pet.control.dto;

public record PetDTO(Long id, String petName, String petBreed, String
		                 petKind, String gender, Long owner,String ownerName,
						 String cpf ) {

}
