package distribuidas.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.models.Client;
import distribuidas.backend.repositories.ClientRepository;
import distribuidas.backend.services.IClientService;

@Service
public class ClientService implements IClientService{

    @Autowired
    private ClientRepository clientRepo;

    @Override
    public Client getClientById(int id) {
        return clientRepo.findClientById(id);
    }
    
}
