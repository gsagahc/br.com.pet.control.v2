package br.com.pet.control.repository;

import br.com.pet.control.model.PetOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwnerEntity, Long>{


}
