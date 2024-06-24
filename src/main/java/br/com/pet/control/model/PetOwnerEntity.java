package br.com.pet.control.model;



import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty("Pet owner name")
	@Column(name = "name")
	private String name;
	@ApiModelProperty("Pet owner cpf")
	@Column(name = "cpf")
	private String cpf;
	@ApiModelProperty("Pet owner e-mail")
	@Column(name = "email")
	private String email;
	@ApiModelProperty("Pet owner gender Male, Female")
	@Column(name = "gender")
	private String gender;
	@ApiModelProperty("Pet owner phone number")
	@Column(name = "phone_number")
	private String phone_number;

	@ApiModelProperty("Pet owner address")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_address", referencedColumnName = "id")
	@JsonProperty("owner_address")
	private AddressEntity ownerAddress;
	@ApiModelProperty("List of pets")
	@OneToMany(mappedBy = "fk_cpf", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<PetEntity> petList;


}

	
	

	

	
