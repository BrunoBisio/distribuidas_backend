package distribuidas.backend.services;

import distribuidas.backend.dtos.ProductDto;

public interface ICatalogItemService {
    public ProductDto getByAuctionId(int auctionId);
}
