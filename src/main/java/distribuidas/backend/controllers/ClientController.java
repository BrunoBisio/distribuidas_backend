package distribuidas.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.services.IClientService;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private IClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> index(@PathVariable int id) {
        ClientDto client = service.getClientById(id);
        return ResponseEntity.ok(client);
    }
}