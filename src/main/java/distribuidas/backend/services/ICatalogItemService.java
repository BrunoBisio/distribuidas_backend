package distribuidas.backend.services;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.models.CatalogItem;

public interface ICatalogItemService {
    public ProductDto getByAuctionId(int auctionId);

    public CatalogItem getById(int id);
}
