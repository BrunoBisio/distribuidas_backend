package distribuidas.backend.mappers;

import distribuidas.backend.dtos.BidDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.models.Assistant;
import distribuidas.backend.models.Bid;
import distribuidas.backend.models.CatalogItem;

public class BidMapper {

    public static BidDto toDto(Bid bid) {
        BidDto dto = new BidDto();
        dto.setAmmount(bid.getAmmount());
        dto.setCreated(bid.getCreated());
        return dto;
    }

    public static Bid fromDto(BidDto dto, CatalogItem item, Assistant assistant, Admited admited) {
        Bid bid = new Bid();
        bid.setAmmount(dto.getAmmount());
        bid.setCreated(dto.getCreated());
        bid.setItem(item);
        bid.setAssistant(assistant);
        bid.setWinner(admited);
        return bid;
    }
}