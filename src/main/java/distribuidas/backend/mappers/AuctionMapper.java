package distribuidas.backend.mappers;

import java.util.stream.Collectors;

import distribuidas.backend.dtos.AuctionDto;
import distribuidas.backend.dtos.BasicAuctionDto;
import distribuidas.backend.models.Auction;

public class AuctionMapper {
    
    public static AuctionDto toDto(Auction auction){
        AuctionDto  dto = new AuctionDto();
        dto.setId(auction.getId());
        dto.setCategory(auction.getCategory());
        dto.setStatus(auction.getState());
        dto.setStartDate(auction.getOpenDate());
        dto.setDescription(auction.getDescription());
        dto.setProductDtos(auction.getProducts().stream().map(ProductMapper::toDto).collect(Collectors.toList()));
        dto.setPhoto(auction.getPhoto());
        return dto;
    }

    public static BasicAuctionDto toBasicDto(Auction auction) {
        BasicAuctionDto dto = new BasicAuctionDto();
        dto.setName(auction.getName());
        dto.setQuantity(auction.getProducts().size());
        dto.setStartDate(auction.getOpenDate());
        dto.setPhoto(auction.getPhoto());
        return dto;
    }
}

