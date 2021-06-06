package distribuidas.backend.services;

import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.dtos.UserDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.Status;
import distribuidas.backend.models.Client;
import distribuidas.backend.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public interface IClientService {
    public ClientDto getClientById(int id);
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
    public void save(UserDto userdto);
    public UserDto getByEmail(String email);
}