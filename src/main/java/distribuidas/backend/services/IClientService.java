package distribuidas.backend.services;

import org.springframework.stereotype.Service;

import distribuidas.backend.models.Client;

@Service
public interface IClientService {
    public Client getClientById(int id);
}