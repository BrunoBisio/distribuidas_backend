package distribuidas.backend.services;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.models.CatalogItem;

public interface ICatalogItemService {
    public ProductDto getByAuctionId(int auctionId) throws Exception;

    public CatalogItem getById(int id);
}
