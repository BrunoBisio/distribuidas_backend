package distribuidas.backend.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.ProductDto;
import distribuidas.backend.enums.Admited;
import distribuidas.backend.mappers.ProductMapper;
import distribuidas.backend.models.Bid;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.repositories.BidRepository;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.services.ICatalogItemService;

@Service
public class CatalogItemServiceImpl implements ICatalogItemService {

    private final long MAX_TIME = 5; // tiempo en milisegundos
    
    @Autowired
    private CatalogItemRepository catalogItemRepository;
    @Autowired
    private BidRepository bidRepository;

    @Override
    public ProductDto getByAuctionId(int auctionId) {
        /*
        - Tiene un endpoint que devuelve el itemCatalogo activo
        - Valida hace cuanto se realizo la ultima puja para el ítem
            - si el tiempo no supera el tiempo máximo, devuelve el mismo ítem.
            - en caso contrario cierra el ítemCatalogo y comienza a pasar el siguiente
        Tiene un endpoint que para un itemCategory devuelve la ultima puja ganadora o el usuario de la ultima puja ganadora.
         (a definir)
        */
        /**
         * Desgloce:
         * 1 - buscar el itemCatalogo no vendido para la subasta *
         * 2 - obtener la ultima puja para el itemCatalogo
         *  2.a - si no hay pujas se devuelve el itemCatalogo
         *  2.b - si hay al menos una puja obtener hace cuanto se hizo
         *      2.b.a - si se hizo hace menos de N tiempo, devolver el itemCatalogo
         *      2.b.b - si la diferencia es menor o igual a N, sigo a 3
         * 3 - crear AuctionRegistry para el cliente y el itemCatalogo
         * 4 - setear como vendido el ItemCatalogo
         * 5 - volver a buscar el itemCatalogo no vendido para la subasta
         * 
         * Notas:
         *  - La busqueda de itemCatalogo debe ser: obtener el primero, no vendido para la subasta
        */
        CatalogItem item = catalogItemRepository.findFirstByCatalogAuctionIdAndAuctionedOrderByIdAsc(auctionId, Admited.no);
        Bid latestBid = bidRepository.findFirstByItemIdOrderByIdDesc(item.getId());
        if (latestBid != null) {
            if (latestBid.getCreated().getTime() - new Date().getTime() >= MAX_TIME) {
                item.setAuctioned(Admited.si);
                catalogItemRepository.save(item);
                item = catalogItemRepository.findFirstByCatalogAuctionIdAndAuctionedOrderByIdAsc(auctionId, Admited.no);
            }

            item.getProduct().setPrice(latestBid.getAmmount());
        } else {
            item.getProduct().setPrice(item.getBasePrice());
        }
        
        return ProductMapper.toDto(item.getProduct());
    }
    
}