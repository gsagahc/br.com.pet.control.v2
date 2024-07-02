package br.com.pet.control.dto;

import br.com.pet.control.model.PermissionEntity;
import br.com.pet.control.model.User_permissionEntity;

import java.util.List;
import java.util.Set;

public record UserDetailsDTO(String login, String fullName, Boolean
		                     accountNonExpired, Boolean accountNonLocked, Boolean
		                     credentialsNonExpired, Boolean enabled,
							 List<PermissionEntity> permissions) {

	
	
	
	
	
	
	
	
	
}
