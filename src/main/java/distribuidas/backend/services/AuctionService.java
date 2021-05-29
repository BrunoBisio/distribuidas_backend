package distribuidas.backend.services;

import distribuidas.backend.dtos.AuctionDto;
import distribuidas.backend.dtos.AuctionList;

public interface AuctionService {
    public AuctionList getAuctions();
    public AuctionList getAuctionsForUser(int id);
    public AuctionDto getAuctionById(int id);
}
