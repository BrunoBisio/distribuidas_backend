package distribuidas.backend.services;

import java.util.List;

import distribuidas.backend.dtos.BidsCreatedDto;
import distribuidas.backend.dtos.ParticipatedAuctionDto;
import distribuidas.backend.dtos.PublishedProductsDto;
import distribuidas.backend.dtos.AuctionsWonDto;

public interface ISummaryService {
    public long getAuctionedAuctions(int clientId);
    public long getBidsCreated(int clientId);
    public long getAuctionsWon(int clientId);
    public long getProductsPublished(int clientId);
    public List<ParticipatedAuctionDto> getAuctionedAuctionsDetails(int clientId);
    public List<BidsCreatedDto> getBidsCreatedDetails(int clientId);
    public List<AuctionsWonDto> getAuctionsWonDetails(int clientId);
    public List<PublishedProductsDto> getProductsPublishedDetails(int clientId);
}
