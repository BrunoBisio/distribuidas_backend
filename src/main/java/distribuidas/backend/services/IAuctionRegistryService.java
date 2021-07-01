package distribuidas.backend.services;

import java.util.List;

import distribuidas.backend.dtos.AuctionRegistryDto;
import distribuidas.backend.dtos.PaymentMethodDto;

public interface IAuctionRegistryService {
    public List<AuctionRegistryDto> getBoughtProducts(int clientId);

    public boolean create(int itemId, PaymentMethodDto payment, int clientId) throws Exception;
}
