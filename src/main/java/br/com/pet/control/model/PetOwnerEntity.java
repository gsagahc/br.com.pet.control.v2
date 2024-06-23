package br.com.pet.control.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "owner_pet")
public class PetOwnerEntity implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "email")
	private String email;

	@Column(name = "gender")
	private String gender;

	@Column(name = "phone_number")
	private String phone_number;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinTable(name = "address", joinColumns = {@JoinColumn (name = "id")},
			inverseJoinColumns = {@JoinColumn (name = "owner_id")})
		private AddressEntity address;


	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
	private List<PetEntity> petList;


}

	
	

	

	
