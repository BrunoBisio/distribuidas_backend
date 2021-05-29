package distribuidas.backend.mappers;

import distribuidas.backend.dtos.AuctionDto;
import distribuidas.backend.models.Auction;

import java.util.stream.Collectors;

public class AuctionMapper {
    
    public static AuctionDto toDto(Auction auction){
        AuctionDto  dto = new AuctionDto();
        dto.setId(auction.getId());
        dto.setCategory(auction.getCategory());
        dto.setStatus(auction.getState());
        dto.setStartDate(auction.getOpenDate());
        dto.setDescription(auction.getDescription());
        dto.setProductDtos(auction.getProducts().stream().map(ProductMapper::toDto).collect(Collectors.toList()));
        return dto;
    }
}

