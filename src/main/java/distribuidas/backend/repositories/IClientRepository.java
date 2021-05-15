package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Client;

public interface IClientRepository extends JpaRepository<Client, Integer>{
    
    Client findClientById(int id);
}
