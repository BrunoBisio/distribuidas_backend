package distribuidas.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.dtos.AuctionRegistryDto;
import distribuidas.backend.security.Context;
import distribuidas.backend.services.IAuctionRegistryService;

@RestController
@RequestMapping("")
public class AuctionRegistryController {
    @Autowired
    private IAuctionRegistryService service;

    @GetMapping("/item/bought")
    public List<AuctionRegistryDto> getBoughtProducts() { return service.getBoughtProducts(Context.getPrincipalId()); }
}
