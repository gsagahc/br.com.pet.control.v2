package br.com.pet.control.repository;

import br.com.pet.control.model.PetCareEntity;
import br.com.pet.control.model.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PetCareRepository extends JpaRepository<PetCareEntity, Long>{
    public List<PetCareEntity> findByPet(Optional<PetEntity> pet);


}
