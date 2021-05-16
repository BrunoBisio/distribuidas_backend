package distribuidas.backend.mappers;

import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.models.Client;

public class ClientMapper {
    
    public static ClientDto toDto(Client client){
        ClientDto dto = new ClientDto();
        dto.setName(client.getUser().getName());
        dto.setIdentityNumber(client.getUser().getName());
        dto.setStatus(client.getUser().getStatus());
        dto.setPicture(client.getUser().getPicture());
        dto.setCategory(client.getCategory());
        return dto;
    }
}
