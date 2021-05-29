package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
}
