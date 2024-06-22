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
@Table(name = "ownerpet")
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
	private Boolean gender;

	@Column(name = "phone_number")
	private Boolean phone_number;


	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "address", joinColumns = {@JoinColumn (name = "owner_id")},
		inverseJoinColumns = {@JoinColumn (name = "id")}
	)
	private AddressEntity address;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pet_cad", joinColumns = {@JoinColumn (name = "pet_owner")},
			inverseJoinColumns = {@JoinColumn (name = "id")}
	)
	private List<PetEntity> petList;


}

	
	

	

	
