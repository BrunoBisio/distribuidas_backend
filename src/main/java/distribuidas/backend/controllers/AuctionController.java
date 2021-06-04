package distribuidas.backend.controllers;

import distribuidas.backend.dtos.AuctionDto;
import distribuidas.backend.dtos.AuctionList;
import distribuidas.backend.services.IAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return auctionService.getAuctionsForUser(1);
    }

    @GetMapping("/soon")
    public AuctionList getFutureAuctions() { return auctionService.getFututreAuctions(); }

    @GetMapping("/user/soon")
    public AuctionList getFutureAuctionsForUser() {
        return auctionService.getFututreAuctionsForUser(1);
    }

    @GetMapping("/{id}")
    public AuctionDto getAuctionById(@PathVariable int id) {
        return auctionService.getAuctionById(id);
    }
}
