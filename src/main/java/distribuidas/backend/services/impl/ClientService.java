package distribuidas.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import distribuidas.backend.models.Client;
import distribuidas.backend.repositories.IClientRepository;
import distribuidas.backend.services.IClientService;

public class ClientService implements IClientService{

    @Autowired
    private IClientRepository clientRepo;

    @Override
    public Client getClientById(int id) {
        return clientRepo.findClientById(id);
    }
    
}
