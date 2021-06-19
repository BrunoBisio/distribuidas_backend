package distribuidas.backend.services.impl;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.BidDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.enums.Category;
import distribuidas.backend.mappers.BidMapper;
import distribuidas.backend.models.Assistant;
import distribuidas.backend.models.Auction;
import distribuidas.backend.models.Bid;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.models.Client;
import distribuidas.backend.repositories.AssistantRepository;
import distribuidas.backend.repositories.AuctionRepository;
import distribuidas.backend.repositories.BidRepository;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.repositories.ClientRepository;
import distribuidas.backend.services.IBidService;

@Service
public class BidService implements IBidService {

    @Autowired
    private AssistantRepository assistantRepository;
    @Autowired
    private CatalogItemRepository catalogItemRepository;
    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AuctionRepository auctionRepository;

    @Override
    public BidDto createBid(int auctionId, int productId, BidDto dto, int clientId) throws Exception {
        BidDto savedBidDto = null;
        Assistant assistant = assistantRepository.findByClientIdAndAuctionId(clientId, auctionId);
        // codigo fresquito
        if (assistant == null) {
            Client client = clientRepository.findById(clientId).get();
            Auction auction = auctionRepository.findById(auctionId).get();
            assistant = new Assistant();
            assistant.setBidderNumber(new Random().nextInt(1000)); 
            assistant.setClient(client);
            assistant.setAuction(auction);
            assistant = assistantRepository.save(assistant);
        }
        
        CatalogItem item = catalogItemRepository.findById(productId).get();
        // Los pujos deben ser mayor al 1% del precio base
        if (item.getBasePrice().multiply(BigDecimal.valueOf(1.01)).compareTo(dto.getAmmount()) < 0) {
            Bid latestBid = bidRepository.findFirstByItemIdOrderByIdDesc(item.getId());
            if (latestBid.getAssistant().getAssistantId() == assistant.getAssistantId()) {
                throw new Exception("Por favor, espere a que alguien mas oferte para realizar una nueva oferta");
            }
            Category auctionCategory = assistant.getAuction().getCategory();
            BigDecimal currentPrice = latestBid != null ? latestBid.getAmmount() : item.getBasePrice();
            // y deben ser menores al 20% del precio actual, salvo que la subasta sea Oro o Platino
            if (auctionCategory.ordinal() == Category.oro.ordinal() 
                || auctionCategory.ordinal() == Category.platino.ordinal()
                || currentPrice.multiply(BigDecimal.valueOf(1.2)).compareTo(dto.getAmmount()) > 0) {
                    
                    Bid bid = BidMapper.fromDto(dto, item, assistant, Admited.si);
                    bid = bidRepository.save(bid);
                    savedBidDto = BidMapper.toDto(bid);
                    // actualizar pujo previo
                    if (latestBid != null) {
                        latestBid.setWinner(Admited.no);
                        bidRepository.save(latestBid);
                    }
                } else {
                    throw new Exception("El monto ofertado es superior al permitido.");
                }
        } else {
            throw new Exception("El monto ofertado es inferior al mínimo requerido.");
        }
        return savedBidDto;
    }
}
