package br.com.pet.control.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="pet_care")
@JsonPropertyOrder({"id", "petName","petBreed","petKind","gender","petOwner","address","phoneNumber","email"})
public class PetCareEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@ApiModelProperty("Pet Care Id")
	@JsonProperty("Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fk_pet")
	@ApiModelProperty("Pet")
	@JsonProperty("pet")
	private PetEntity pet;

	@ApiModelProperty("Care Date")
	@Column(name="care_date", nullable = false)
	@JsonProperty("care_date")
	private LocalDateTime careDate;

	@ApiModelProperty("Has grooming ('S','N')")
	@Column(name="grooming")
	@JsonProperty("grooming")
	private String hasGrooming;






	

	
	
}
