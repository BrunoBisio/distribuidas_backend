package distribuidas.backend.services;

import distribuidas.backend.dtos.AuctionDto;
import distribuidas.backend.dtos.AuctionList;

public interface IAuctionService {
    public AuctionList getAuctions();
    public AuctionList getAuctionsForUser(int id);
    public AuctionDto getAuctionById(int id);
    public AuctionList getFututreAuctions();
    public AuctionList getFututreAuctionsForUser(int id);
    public boolean closeAuction(int auctionId);
}
