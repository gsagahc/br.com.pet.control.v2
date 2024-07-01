package br.com.pet.control.dto;

import br.com.pet.control.model.PetCareEntity;
import br.com.pet.control.model.PetEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Optional;

public record PetCareCreateDTO(Long petId,
                               String dateTime,
                               String hasGrooming) {



}
