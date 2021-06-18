package distribuidas.backend.mappers;

import distribuidas.backend.dtos.PublishedProductsDto;
import distribuidas.backend.models.AuctionRegistry;
import distribuidas.backend.models.CatalogItem;

public class PublishedProductsMapper {
    
    public static PublishedProductsDto toDto(CatalogItem ci, AuctionRegistry ar) {
        PublishedProductsDto dto = new PublishedProductsDto();
        dto.setProductName(ci.getProduct().getName());
        dto.setBasePrice(ci.getBasePrice());
        dto.setCommisionPercentage(ci.getCommission());
        if (ar != null) {
            dto.setValue(ar.getAmmount());
            dto.setCommisionValue(ar.getCommission());
        }
        return dto;
    }
}
