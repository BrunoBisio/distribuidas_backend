package distribuidas.backend.mappers;

import distribuidas.backend.dtos.AuctionRegistryDto;
import distribuidas.backend.models.AuctionRegistry;

public class AuctionRegistryMapper {
    
    public static AuctionRegistryDto toDto(AuctionRegistry auctionRegistry) {
        AuctionRegistryDto dto = new AuctionRegistryDto(
            auctionRegistry.getProduct().getName(),
            auctionRegistry.getAuction().getOpenDate(),
            auctionRegistry.getAmmount());
        return dto;
    }
}
