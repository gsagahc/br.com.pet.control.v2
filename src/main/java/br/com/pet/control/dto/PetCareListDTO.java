package br.com.pet.control.dto;

import br.com.pet.control.model.PetCareEntity;
import br.com.pet.control.model.PetEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

public record PetCareListDTO(String id,
                             String careDate,
                             String hasGrooming) {


}
