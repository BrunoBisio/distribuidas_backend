package distribuidas.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.security.Context;
import distribuidas.backend.services.ISummaryService;

@RestController
@RequestMapping("summary")
public class SummaryController {
    @Autowired
    private ISummaryService service;
    
    // subastas en las que participo
    @GetMapping("/auction/assisted")
    public long getAuctionedAuctions() { return service.getAuctionedAuctions(Context.getPrincipalId()); }

    // subastas en las que gano
    @GetMapping("/auction/won")
    public long getAuctionsWon() { return service.getAuctionsWon(Context.getPrincipalId()); }

    // subastas en las que oferto
    @GetMapping("/bids")
    public long getBidsCreated() { return service.getBidsCreated(Context.getPrincipalId()); }

    // subastas en las que publico
    @GetMapping("/products/published")
    public long getProductsPublished() { return service.getProductsPublished(Context.getPrincipalId()); }


}
