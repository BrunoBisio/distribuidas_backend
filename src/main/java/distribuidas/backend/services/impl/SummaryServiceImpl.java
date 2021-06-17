package distribuidas.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.enums.Admited;
import distribuidas.backend.repositories.AssistantRepository;
import distribuidas.backend.repositories.ProductRepository;
import distribuidas.backend.services.ISummaryService;

@Service
public class SummaryServiceImpl implements ISummaryService {

    @Autowired
    private ProductRepository prodRepository;
    @Autowired
    private AssistantRepository assistantRepository;

    @Override
    public long getAuctionedAuctions(int clientId) {
        return assistantRepository.countByClientId(clientId);
    }

    @Override
    public long getProductsPublished(int clientId) {
        return prodRepository.countByOwnerIdAndApproved(clientId, Admited.si);
    }

    @Override
    public long getAuctionsWon(int clientId) {
        return assistantRepository.countByAuctionsWon(clientId);
    }

    @Override
    public long getBidsCreated(int clientId) {
        return assistantRepository.countbyBidsCreated(clientId);
    }
    
}
