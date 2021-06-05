package distribuidas.backend.services.impl;

import distribuidas.backend.enums.Category;
import distribuidas.backend.mappers.BidMapper;
import distribuidas.backend.models.*;
import distribuidas.backend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.BidDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.repositories.AssistantRepository;
import distribuidas.backend.repositories.BidRepository;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.services.IBidService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BidService implements IBidService {

    @Autowired
    private AssistantRepository assistantRepository;
    @Autowired
    private CatalogItemRepository catalogItemRepository;
    @Autowired
    private BidRepository bidRepository;

    @Override
    public BidDto createBid(int auctionId, int productId, BidDto dto) {
        BidDto savedBidDto = null;
        int clientId = 1; // TODO: obtener el clientId mediante el authToken
        Assistant assistant = assistantRepository.findByClientIdAndAuctionId(clientId, auctionId);
        CatalogItem item = catalogItemRepository.findById(productId).get();
        Bid latestBid = bidRepository.findFirstByItemIdOrderByBidIdDesc(item.getCatalogItemId());
        BigDecimal currentPrice = latestBid != null ? latestBid.getAmmount() : item.getBasePrice();
        if (isBidValid(currentPrice, dto.getAmmount(), assistant.getAuction().getCategory())) {
            Bid bid = BidMapper.fromDto(dto, item, assistant, Admited.si);
            bid = bidRepository.save(bid);
            savedBidDto = BidMapper.toDto(bid);
            // update previous bid
            latestBid.setWinner(Admited.no);
            bidRepository.save(latestBid);
        }
        return savedBidDto;
    }

    public boolean isBidValid(BigDecimal price, BigDecimal bid, Category auctionCategory) {
        // mayor al 1%
        if (bid.compareTo(price.multiply(BigDecimal.valueOf(1.01))) > -1) {
            if (auctionCategory.ordinal() == Category.oro.ordinal() || auctionCategory.ordinal() == Category.plata.ordinal()) {
                return true;
            }
            // menor al 20%
            return bid.compareTo(price.multiply(BigDecimal.valueOf(1.2))) < 1;
        }
        return false;
    }
}
