package distribuidas.backend.mappers;

import distribuidas.backend.dtos.AuctionsWonDto;
import distribuidas.backend.models.AuctionRegistry;

public class AuctionsWonMapper {
    
    public static AuctionsWonDto toDto(AuctionRegistry ar) {
        AuctionsWonDto dto = new AuctionsWonDto();
        dto.setAuctionName(ar.getAuction().getName());
        dto.setProductName(ar.getProduct().getName());
        dto.setValue(ar.getAmmount());
        dto.setCurrency(ar.getAuction().getCurrency());
        dto.setAuctionDate(ar.getAuction().getOpenDate());
        return dto;
    }
}
