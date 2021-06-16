package distribuidas.backend.services;

import java.util.List;

import distribuidas.backend.dtos.ProductDto;

public interface IProductService {

    List<ProductDto> getSoldProducts(int clientId);
    List<ProductDto> getActiveAuctionProducts(int clientId);
    List<ProductDto> getPendingAuctionProducts(int clientId);
    List<ProductDto> getUnapprovedProducts(int clientId);
}
