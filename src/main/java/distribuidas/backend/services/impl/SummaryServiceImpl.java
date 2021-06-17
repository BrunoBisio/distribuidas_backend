package distribuidas.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.BidDetailDto;
import distribuidas.backend.dtos.ParticipatedAuctionDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.mappers.BidDetailMapper;
import distribuidas.backend.mappers.ParticipatedAuctionMapper;
import distribuidas.backend.repositories.AssistantRepository;
import distribuidas.backend.repositories.BidRepository;
import distribuidas.backend.repositories.ProductRepository;
import distribuidas.backend.services.ISummaryService;

@Service
public class SummaryServiceImpl implements ISummaryService {

    @Autowired
    private ProductRepository prodRepository;
    @Autowired
    private AssistantRepository assistantRepository;
    @Autowired
    private BidRepository bidRepository;

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
        return bidRepository.countByAssistantClientIdAndWinner(clientId, Admited.si);
    }

    @Override
    public long getBidsCreated(int clientId) {
        return bidRepository.countByAssistantClientId(clientId);
    }

    @Override
    public List<ParticipatedAuctionDto> getParticipatedAuctionsDetails(int clientId) {
        return bidRepository.findByAssistantClientId(clientId).stream()
            .map(ParticipatedAuctionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BidDetailDto> getBidCreatedDetails(int clientId) {
        return bidRepository.findByAssistantClientId(clientId).stream()
            .map(BidDetailMapper::toDto).collect(Collectors.toList());
    }
    
}
