package distribuidas.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.dtos.BidDto;
import distribuidas.backend.security.Context;
import distribuidas.backend.services.IBidService;

@RestController
@RequestMapping("")
public class BidController {
    @Autowired
    private IBidService bidService;

    @PostMapping("/auction/{auctionId}/product/{productId}/bid")
    public BidDto index(@PathVariable int auctionId, @PathVariable int productId, @RequestBody BidDto dto) {
        // Context.getPrincipalId()
        return bidService.createBid(auctionId, productId, dto, 11);
    }
}
