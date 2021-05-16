package distribuidas.backend.services;

import distribuidas.backend.dtos.ClientDto;

public interface IClientService {
    public ClientDto getClientById(int id);
}