package distribuidas.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.mappers.ClientMapper;
import distribuidas.backend.models.Client;
import distribuidas.backend.repositories.ClientRepository;
import distribuidas.backend.services.IClientService;

@Service
public class ClientService implements IClientService{

    @Autowired
    private ClientRepository clientRepo;

    @Override
    public ClientDto getClientById(int id) {
        Client client = clientRepo.findClientById(id);
        return ClientMapper.toDto(client);
    }
    
}
