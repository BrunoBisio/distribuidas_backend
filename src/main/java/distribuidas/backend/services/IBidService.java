package distribuidas.backend.services;

import distribuidas.backend.dtos.BidDto;

public interface IBidService {

    boolean createBid(int auctionId, int productId, BidDto dto);
    
}
