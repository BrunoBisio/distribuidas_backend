package distribuidas.backend.mappers;

import java.util.Date;

import distribuidas.backend.dtos.BidDto;
import distribuidas.backend.models.Assistant;
import distribuidas.backend.models.Bid;
import distribuidas.backend.models.CatalogItem;

public class BidMapper {

    public static BidDto toDto(Bid bid) {
        BidDto dto = new BidDto();
        dto.setAmmount(bid.getAmmount());
        dto.setCreated(bid.getCreated());
        dto.setId(bid.getAssistant().getAssistantId());
        return dto;
    }

    public static Bid fromDto(BidDto dto, CatalogItem item, Assistant assistant) {
        Bid bid = new Bid();
        bid.setAmmount(dto.getAmmount());
        bid.setCreated(new Date());
        bid.setItem(item);
        bid.setAssistant(assistant);
        return bid;
    }
}