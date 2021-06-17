package distribuidas.backend.mappers;

import distribuidas.backend.dtos.BidsCreatedDto;
import distribuidas.backend.models.Bid;

public class BidDetailMapper {
    
    public static BidsCreatedDto toDto(Bid bid) {
        BidsCreatedDto dto = new BidsCreatedDto();
        dto.setAuctionName(bid.getItem().getCatalog().getAuction().getName());
        dto.setProductName(bid.getItem().getProduct().getName());
        dto.setValue(bid.getAmmount());
        dto.setBidDate(bid.getCreated());
        dto.setWinner(bid.getWinner().name());
        return dto;
    }
}
