package distribuidas.backend.mappers;

import distribuidas.backend.dtos.ParticipatedAuctionDto;
import distribuidas.backend.models.Assistant;

public class ParticipatedAuctionMapper {
    
    public static ParticipatedAuctionDto toDto(Assistant assistant) {
        ParticipatedAuctionDto dto = new ParticipatedAuctionDto();
        dto.setAuctionName(assistant.getAuction().getName());
        dto.setCategory(assistant.getAuction().getCategory());
        dto.setAuctionDate(assistant.getAuction().getOpenDate());
        dto.setCurrency(assistant.getAuction().getCurrency());
        return dto;
    }
}
