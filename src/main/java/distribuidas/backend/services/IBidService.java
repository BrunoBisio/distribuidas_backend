package distribuidas.backend.services;

import distribuidas.backend.dtos.BidDto;

public interface IBidService {

    BidDto createBid(int auctionId, int productId, BidDto dto, int clientId);
    
}
