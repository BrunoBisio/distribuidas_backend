package distribuidas.backend.services;

import java.util.List;

import distribuidas.backend.dtos.BidDetailDto;
import distribuidas.backend.dtos.ParticipatedAuctionDto;

public interface ISummaryService {
    public long getAuctionedAuctions(int clientId);
    public long getBidsCreated(int clientId);
    public long getAuctionsWon(int clientId);
    public long getProductsPublished(int clientId);
    public List<ParticipatedAuctionDto> getParticipatedAuctionsDetails(int clientId);
    public List<BidDetailDto> getBidCreatedDetails(int clientId);
}
