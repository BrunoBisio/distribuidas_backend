package distribuidas.backend.mappers;

import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.models.Client;

public class ClientMapper {
    
    public static ClientDto toDto(Client client){
        ClientDto dto = new ClientDto();
        dto.setName(client.getUser().getName());
        dto.setIdentityNumber(client.getUser().getIdentityNumber());
        dto.setStatus(client.getUser().getStatus());
        dto.setPicture(client.getUser().getPicture());
        dto.setCategory(client.getCategory());
        dto.setAddress(client.getUser().getAddress());
        dto.setEmail(client.getUser().getEmail());
        dto.setPhoneNumber(client.getUser().getPhoneNumber());
        return dto;
    }
}
