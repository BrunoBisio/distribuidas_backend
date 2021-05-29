package distribuidas.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import distribuidas.backend.dtos.BidDto;
import distribuidas.backend.services.IBidService;

@RestController
@RequestMapping("")
public class BidController {
    @Autowired
    private IBidService service;

    @ResponseStatus
    @PostMapping("/auction/{auctionId}/product/{productId}/bid")
    public ResponseEntity<Object> index(@PathVariable int auctionId, @PathVariable int productId, @RequestBody BidDto dto) {
        if (service.createBid(auctionId, productId, dto)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.badRequest().build();
    }
}
