package distribuidas.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.AuctionsWonDto;
import distribuidas.backend.dtos.BidsCreatedDto;
import distribuidas.backend.dtos.ParticipatedAuctionDto;
import distribuidas.backend.dtos.PublishedProductsDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.mappers.AuctionsWonMapper;
import distribuidas.backend.mappers.BidDetailMapper;
import distribuidas.backend.mappers.ParticipatedAuctionMapper;
import distribuidas.backend.mappers.PublishedProductsMapper;
import distribuidas.backend.models.AuctionRegistry;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.repositories.AssistantRepository;
import distribuidas.backend.repositories.AuctionRegistryRepository;
import distribuidas.backend.repositories.BidRepository;
import distribuidas.backend.repositories.CatalogItemRepository;
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
    @Autowired
    private AuctionRegistryRepository arRepository;
    @Autowired
    private CatalogItemRepository ciRepository;

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
    public List<ParticipatedAuctionDto> getAuctionedAuctionsDetails(int clientId) {
        return assistantRepository.findByClientId(clientId).stream()
            .map(ParticipatedAuctionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BidsCreatedDto> getBidsCreatedDetails(int clientId) {
        return bidRepository.findByAssistantClientId(clientId).stream()
            .map(BidDetailMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AuctionsWonDto> getAuctionsWonDetails(int clientId) {
        return arRepository.findByClientId(clientId).stream()
            .map(AuctionsWonMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PublishedProductsDto> getProductsPublishedDetails(int clientId) {
        List<CatalogItem> cis = ciRepository.findByProductOwnerIdAndProductApproved(clientId, Admited.si);
        List<AuctionRegistry> ars = arRepository.findByOwnerIdAndProductIn(clientId, 
            cis.stream().map(CatalogItem::getProduct).collect(Collectors.toList()));
        return cis.stream().map((ci) -> {
            if (ars.stream().filter((ar) -> ar.getProduct().getId() == ci.getProduct().getId()).count() > 0) {
                return PublishedProductsMapper.toDto(ci, 
                    ars.stream().filter((ar) -> { return ar.getProduct().getId() == ci.getProduct().getId(); }).findFirst().get() );
            } else {
                return PublishedProductsMapper.toDto(ci, null);
            }
        }).collect(Collectors.toList());
    }
}
