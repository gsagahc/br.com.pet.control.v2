package br.com.pet.control.model;



import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
	@Column(name="pet_name", nullable = false, length=80)
	@JsonProperty("Pet_name")
	private String petName;
	@Column(name="pet_breed", nullable = false, length=50)
	@JsonProperty("Pet_breed")
	private String petBreed;
	@Column(name="pet_kind", nullable =  false, length=50)
	@JsonProperty("Pet_kind")
	private String petKind;
	@Column( nullable = false)
	@JsonProperty("Pet_gender")
	private String gender;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_id")
	private PetOwnerEntity owner;


	

	
	
}
