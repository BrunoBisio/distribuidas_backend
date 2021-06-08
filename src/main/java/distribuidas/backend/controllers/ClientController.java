package distribuidas.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.security.Context;
import distribuidas.backend.services.IClientService;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private IClientService service;

    @GetMapping("")
    public ClientDto index() { return service.getClientById(Context.getPrincipalId()); }
}