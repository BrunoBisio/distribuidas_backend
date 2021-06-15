package distribuidas.backend.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.dtos.UserDto;

public interface IClientService {
    public ClientDto getClientById(int id);
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
    public void save(UserDto userdto);
    public UserDto getByEmail(String email);
    public ClientDto updateClient(int id, UserDto userDto);
}