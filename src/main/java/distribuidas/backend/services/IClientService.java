package distribuidas.backend.services;

import distribuidas.backend.models.Client;

public interface IClientService {
    public Client getClientById(int id);
}