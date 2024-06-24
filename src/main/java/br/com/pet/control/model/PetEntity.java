package br.com.pet.control.model;



import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="pet_cad")
@JsonPropertyOrder({"id", "petName","petBreed","petKind","gender","petOwner","address","phoneNumber","email"})
public class PetEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ApiModelProperty("Pet name")
	@Column(name="pet_name", nullable = false, length=80)
	@JsonProperty("Pet_name")
	private String petName;
	@ApiModelProperty("Pet obreed")
	@Column(name="pet_breed", nullable = false, length=50)
	@JsonProperty("Pet_breed")
	private String petBreed;
	@ApiModelProperty("Pet kind (cat, dog, mouse, etc)")
	@Column(name="pet_kind", nullable =  false, length=50)
	@JsonProperty("Pet_kind")
	private String petKind;
	@ApiModelProperty("Pet gender")
	@Column( nullable = false)
	@JsonProperty("Pet_gender")
	private String gender;
	@ApiModelProperty("Pet owner")
	@Column( nullable = false)
	@JsonProperty("owner_cpf")
	private Long fk_cpf;


	

	
	
}
