package br.com.pet.control.repository;

import br.com.pet.control.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long>{


}
