package distribuidas.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.dtos.AuctionDto;
import distribuidas.backend.dtos.AuctionList;
import distribuidas.backend.security.Context;
import distribuidas.backend.services.IAuctionService;

@RestController
@RequestMapping("/auction")
public class AuctionController {
    @Autowired
    private IAuctionService auctionService;
    @GetMapping
    public AuctionList getAuctions() {
        return auctionService.getAuctions();
    }

    @GetMapping("/user")
    public AuctionList getAuctionsForUser() {
        return auctionService.getAuctionsForUser(Context.getPrincipalId());
    }

    @GetMapping("/soon")
    public AuctionList getFutureAuctions() { return auctionService.getFututreAuctions(); }

    @GetMapping("/user/soon")
    public AuctionList getFutureAuctionsForUser() {
        return auctionService.getFututreAuctionsForUser(Context.getPrincipalId());
    }

    @GetMapping("/{id}")
    public AuctionDto getAuctionById(@PathVariable int id) {
        return auctionService.getAuctionById(id);
    }
}
