package distribuidas.backend.services;

import java.util.List;

import distribuidas.backend.dtos.ProductDto;

public interface IProductService {
    public List<ProductDto> getSoldProducts(int clientId);
    public List<ProductDto> getActiveAuctionProducts(int clientId);
    public List<ProductDto> getPendingAuctionProducts(int clientId);
    public List<ProductDto> getUnapprovedProducts(int clientId);
}
