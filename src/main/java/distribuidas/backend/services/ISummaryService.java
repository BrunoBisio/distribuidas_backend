package distribuidas.backend.services;

public interface ISummaryService {

    long getAuctionedAuctions(int clientId);
    
    long getBidsCreated(int clientId);

    long getAuctionsWon(int clientId);

    long getProductsPublished(int clientId);
}
