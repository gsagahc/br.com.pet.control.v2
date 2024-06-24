package br.com.pet.control.repository;

import br.com.pet.control.model.PetCareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetCareRepository extends JpaRepository<PetCareEntity, Long>{


}
