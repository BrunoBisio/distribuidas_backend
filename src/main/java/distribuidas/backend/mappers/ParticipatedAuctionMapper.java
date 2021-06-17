package distribuidas.backend.mappers;

import distribuidas.backend.dtos.ParticipatedAuctionDto;
import distribuidas.backend.models.Bid;

public class ParticipatedAuctionMapper {
    
    public static ParticipatedAuctionDto toDto(Bid bid) {
        ParticipatedAuctionDto dto = new ParticipatedAuctionDto();
        dto.setAuctionName(bid.getItem().getCatalog().getAuction().getName());
        dto.setCategory(bid.getItem().getCatalog().getAuction().getCategory());
        dto.setAuctionDate(bid.getItem().getCatalog().getAuction().getOpenDate());
        dto.setProductName(bid.getItem().getProduct().getName());
        dto.setCurrency(bid.getItem().getProduct().getCurrency());
        return dto;
    }
}
