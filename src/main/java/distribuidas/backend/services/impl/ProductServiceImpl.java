package distribuidas.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.mappers.ProductMapper;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.repositories.ProductRepository;
import distribuidas.backend.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository prodRepository;
    @Autowired
    private CatalogItemRepository ciRepository;

    @Override
    public List<ProductDto> getSoldProducts(int clientId) {
        return ciRepository.findByProductOwnerIdAndAuctioned(clientId, Admited.si).stream()
            .map(CatalogItem::getProduct).map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getActiveAuctionProducts(int clientId) {
        return prodRepository.findActiveAuctionProducts(clientId).stream()
            .map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getPendingAuctionProducts(int clientId) {
        return prodRepository.findPendingAuctionProducts(clientId).stream()
            .map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getUnapprovedProducts(int clientId) {
        return prodRepository.findByOwnerIdAndApproved(clientId, Admited.no).stream()
            .map(ProductMapper::toDto).collect(Collectors.toList());
    }
    
}
