package distribuidas.backend.services;

import java.util.List;

import distribuidas.backend.dtos.AuctionRegistryDto;

public interface IAuctionRegistryService {
    public List<AuctionRegistryDto> getBoughtProducts(int clientId);

    public boolean create(int itemId, int paymentId, int clientId) throws Exception;
}
