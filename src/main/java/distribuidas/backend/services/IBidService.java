package distribuidas.backend.services;

import distribuidas.backend.dtos.BidDto;

public interface IBidService {
    public BidDto createBid(int auctionId, int productId, BidDto dto, int clientId) throws Exception;
}
