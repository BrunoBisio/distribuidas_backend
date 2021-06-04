package distribuidas.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.BidDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.models.Assistant;
import distribuidas.backend.models.Bid;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.repositories.AssistantRepository;
import distribuidas.backend.repositories.BidRepository;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.services.IBidService;

@Service
public class BidService implements IBidService {

    @Autowired
    private AssistantRepository assistantRepository;
    @Autowired
    private CatalogItemRepository catalogItemRepository;
    @Autowired
    private BidRepository bidRepository;

    @Override
    public boolean createBid(int auctionId, int productId, BidDto dto) {
        boolean saved = false;
        int clientId = 1; // TODO: obtener el clientId mediante el authToken
        Assistant assistant = assistantRepository.findByClientIdAndAuctionId(clientId, auctionId);
        CatalogItem item = catalogItemRepository.findById(productId).get();
        // Bid latestBid = bidRepository.findLatestByItemId(item.getCatalogItemId());
        // if ()assistant.getClient().getCategory()
        // if (latestBid == null)
        Bid bid = new Bid();
        bid.setAmmount(dto.getAmmount());
        bid.setItem(item);
        bid.setAssistant(assistant);
        bid.setWinner(Admited.si);
        bid = bidRepository.save(bid);
        saved = bid != null && bid.getBidId() != 0;
        return saved;
    }
    
}
