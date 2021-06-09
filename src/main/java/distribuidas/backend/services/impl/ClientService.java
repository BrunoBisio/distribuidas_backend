package distribuidas.backend.services.impl;

import distribuidas.backend.dtos.UserDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.Status;
import distribuidas.backend.models.User;
import distribuidas.backend.repositories.CountryRepository;
import distribuidas.backend.repositories.EmployeeRepository;
import distribuidas.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.mappers.ClientMapper;
import distribuidas.backend.models.Client;
import distribuidas.backend.repositories.ClientRepository;
import distribuidas.backend.services.IClientService;

import java.util.ArrayList;

@Service
public class ClientService implements IClientService, UserDetailsService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public ClientDto getClientById(int id) {
        Client client = clientRepo.findById(id).get();
        return ClientMapper.toDto(client);
    }

    public UserDto getByEmail(String email) {
        User user = userRepo.findFirstByEmail(email);
        UserDto dto = new UserDto();
        dto.setId(user.getUserId());
        return dto;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findFirstByEmail(s);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public void save(UserDto userdto) {
        User user = new User();
        user.setPassword(bcryptEncoder.encode(userdto.getPassword()));
        user.setEmail(userdto.getEmail());
        user.setIdentityNumber(userdto.getDocument());
        user.setAddress(userdto.getAddress());
        user.setName(userdto.getName());
        user.setPhoneNumber(userdto.getPhoneNumber());
        user.setStatus(Status.inactivo);
        Client client = new Client();
        client.setId(user.getUserId());
        client.setUser(user);
        client.setCountry(countryRepository.findById(1).get());
        client.setAdmited(Admited.no);
        client.setCategory(Category.comun);
        client.setAuthorizedBy(employeeRepository.findById(3).get());
        clientRepo.save(client);
    }
}
