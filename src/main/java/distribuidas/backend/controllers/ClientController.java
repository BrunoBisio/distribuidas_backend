package distribuidas.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.models.Client;
import distribuidas.backend.services.IClientService;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private IClientService service;

    @GetMapping("/id")
    public ResponseEntity<Object> index(@RequestParam int clientId) {
        Client client = service.getClientById(clientId);
        return ResponseEntity.ok(client);
    }
}