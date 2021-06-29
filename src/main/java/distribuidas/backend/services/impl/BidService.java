package distribuidas.backend.services.impl;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.BidDto;
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
import distribuidas.backend.repositories.PaymentMethodRepository;
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
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public BidDto createBid(int auctionId, int productId, BidDto dto, int clientId) throws Exception {
        BidDto savedBidDto = null;
        CatalogItem item = catalogItemRepository.findById(productId).get();
        // revisar que el cliente no sea el dueño del producto
        if (isProductOwner(item, clientId)) {
            throw new Exception("Ups! No podes pujar por tus propios productos");
        }
        
        Client client = clientRepository.findById(clientId).get();
        // revisar que el cliente tenga al menos 1 metodo de pago registrado
        if (!paymentMethodRepository.existsByClientId(clientId)) {
            throw new Exception("Ups! No podes pujar hasta que hayas registrado al menos un metodo de pago.");
        }

        Auction auction = auctionRepository.findById(auctionId).get();
        // revisar que el cliente pertenezca a una categoria igual o mayor a la de la subasta
        if (client.getCategory().ordinal() < auction.getCategory().ordinal()) {
            throw new Exception("Ups! No podes pujar en categorias superiores a la tuya.");
        }

        Assistant assistant = initAssistant(client, auction);
        Bid latestBid = bidRepository.findFirstByItemIdOrderByIdDesc(item.getId());
        BigDecimal currentPrice = latestBid != null ? latestBid.getAmmount() : item.getBasePrice();
        // revisar que el cliente no sea el mismo que la ultima persona que haya pujado para este producto.
        if (latestBid.getAssistant().getAssistantId() == assistant.getAssistantId()) {
            throw new Exception("Por favor, espere a que alguien mas oferte para realizar una nueva oferta");
        }
        
        if (!checkMinValueConstraint(item.getBasePrice(), currentPrice, dto.getAmmount())) {
            throw new Exception("El monto ofertado es inferior al mínimo requerido.");
        }

        Category auctionCategory = assistant.getAuction().getCategory();

        if (!isGoldOrPlatinum(auctionCategory) && !checkMaxValueConstraint(currentPrice, dto.getAmmount())) {
            throw new Exception("El monto ofertado es superior al permitido.");
        }
            
        Bid bid = BidMapper.fromDto(dto, item, assistant);
        bid = bidRepository.save(bid);
        savedBidDto = BidMapper.toDto(bid);

        return savedBidDto;
    }

    // Validaciones de reglas de negocio

    // revisa que el precio sea mayor al 1% del precio base de la subasta mas el precio actual.
    private boolean checkMinValueConstraint(BigDecimal basePrice, BigDecimal currentPrice, BigDecimal bidValue) {
        BigDecimal modifier = basePrice.multiply(BigDecimal.valueOf(0.01));
        return bidValue.compareTo(currentPrice.add(modifier)) > -1;
    }

    // revisa si el cliente es el dueño del producto
    private boolean isProductOwner(CatalogItem item, int clientId) {
        return item.getProduct().getOwner().getId() == clientId;
    }

    // revisa si la categoria recibida es Oro o Platino
    private boolean isGoldOrPlatinum(Category auctionCategory) {
        return auctionCategory.ordinal() == Category.oro.ordinal() || auctionCategory.ordinal() == Category.platino.ordinal();
    }

    // revisa que el valor de la puja sea menor al 120% del precio actual
    private boolean checkMaxValueConstraint(BigDecimal currentPrice, BigDecimal bidValue) {
        return bidValue.compareTo(currentPrice.multiply(BigDecimal.valueOf(1.2))) < 0;
    }

    private Assistant initAssistant(Client client, Auction auction) {
        Assistant assistant = assistantRepository.findByClientIdAndAuctionId(client.getId(), auction.getId());
        // si es la primera vez que participa de la subasta se le crea perfil de 
        // asistente a la subasta.
        if (assistant == null) {
            assistant = new Assistant();
            assistant.setBidderNumber(new Random().nextInt(1000));
            assistant.setClient(client);
            assistant.setAuction(auction);
            assistant = assistantRepository.save(assistant);
        }
        return assistant;
    }
}
