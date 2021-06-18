package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Integer>{
    
}
