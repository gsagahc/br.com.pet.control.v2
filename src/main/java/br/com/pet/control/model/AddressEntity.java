package br.com.pet.control.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "address")
public class AddressEntity implements  Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "ownerpet", joinColumns = {@JoinColumn (name = "owner_id")},
			inverseJoinColumns = {@JoinColumn (name = "id")}
	)
	private Long petOwner;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "uf")
	private String uf;


	public void setPetOwner(PetOwnerEntity petOwner) {
		Long id = petOwner.getId();
		this.petOwner = id;

	}
}

	
	

	

	
