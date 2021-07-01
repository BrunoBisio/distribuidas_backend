package distribuidas.backend.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.AuctionRegistryDto;
import distribuidas.backend.dtos.PaymentMethodDto;
import distribuidas.backend.mappers.AuctionRegistryMapper;
import distribuidas.backend.models.AuctionRegistry;
import distribuidas.backend.models.Bid;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.repositories.AuctionRegistryRepository;
import distribuidas.backend.repositories.BidRepository;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.repositories.PaymentMethodRepository;
import distribuidas.backend.services.IAuctionRegistryService;

@Service
public class AuctionRegistryServiceImpl implements IAuctionRegistryService {

    @Autowired
    private AuctionRegistryRepository arRepository;
    @Autowired
    private CatalogItemRepository catalogItemRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private BidRepository bidRepository;

    @Override
    public List<AuctionRegistryDto> getBoughtProducts(int clientId) {
        return arRepository.findByClientId(clientId).stream()
            .map(AuctionRegistryMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean create(int itemId, PaymentMethodDto payment, int clientId) throws Exception {
        if (arRepository.existsByProductId(itemId))
            return false;
        try {
            CatalogItem item = catalogItemRepository.findByProductId(itemId);
            Bid bid = bidRepository.findFirstByItemProductIdOrderByIdDesc(itemId);
            if (bid == null || bid.getAssistant().getClient().getId() != clientId) {
                return false;
            }
            
            
            AuctionRegistry ar = new AuctionRegistry();
            ar.setAmmount(bid.getAmmount());
            ar.setClient(bid.getAssistant().getClient());
            ar.setCommission(item.getCommission().multiply(bid.getAmmount()).divide(BigDecimal.valueOf(100)));
            ar.setAuction(item.getCatalog().getAuction());
            ar.setOwner(item.getProduct().getOwner());
            ar.setProduct(item.getProduct());
            if (!payment.getCardNumber().isEmpty()) {
                ar.setPaymentMethod(paymentMethodRepository.findByCardNumber(payment.getCardNumber()));
            } else {
                ar.setPaymentMethod(paymentMethodRepository.findByAccountNumber(payment.getAccountNumber()));
            }
            
            arRepository.save(ar);
        } catch (Exception ex) {
            throw new Exception("Ocurrio un problema con el registro de tu pago! Pronto nos comunicaremos contigo para arreglar la venta");
        }
        return true;
    }
    
}
