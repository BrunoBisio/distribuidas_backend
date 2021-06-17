package distribuidas.backend.mappers;

import distribuidas.backend.dtos.BidDetailDto;
import distribuidas.backend.models.Bid;

public class BidDetailMapper {
    
    public static BidDetailDto toDto(Bid bid) {
        BidDetailDto dto = new BidDetailDto();
        dto.setAuctionName(bid.getItem().getCatalog().getAuction().getName());
        dto.setProductName(bid.getItem().getProduct().getName());
        dto.setValue(bid.getAmmount());
        dto.setBidDate(bid.getCreated());
        dto.setWinner(bid.getWinner().name());
        return dto;
    }
}
