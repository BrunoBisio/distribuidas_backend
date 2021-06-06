package distribuidas.backend.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import distribuidas.backend.models.Catalog;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.models.Product;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.repositories.CatalogRepository;
import distribuidas.backend.services.IAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.AuctionDto;
import distribuidas.backend.dtos.AuctionList;
import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.State;
import distribuidas.backend.mappers.AuctionMapper;
import distribuidas.backend.models.Auction;
import distribuidas.backend.repositories.AuctionRepository;
import distribuidas.backend.repositories.PhotoRepository;

@Service
public class AuctionServiceImpl implements IAuctionService {
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private CatalogItemRepository catalogItemRepository;

    @Override
    public AuctionList getAuctions() {
        List<AuctionDto> auctionDto = auctionRepository.findAuctionByState(State.abierta)
                .stream().map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }

    @Override
    public AuctionList getAuctionsForUser(int id) {
        ClientDto client = clientService.getClientById(id);
        List<Category> categories = Arrays.stream(Category.values()).filter(category -> category.ordinal() <= client.getCategory().ordinal() +1).collect(Collectors.toList());
        List<AuctionDto> auctionDto = auctionRepository.findAuctionByStateAndCategoryIn(State.abierta,categories)
                .stream().map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }

    @Override
    public AuctionDto getAuctionById(int id) {
        Auction auction = auctionRepository.findAuctionByStateAndId(State.abierta,id);
        if (auction != null) {
            Catalog catalog = catalogRepository.findByAuctionId(id);
            if (catalog != null) {
                List<CatalogItem> catalogItems = catalogItemRepository.findByCatalogId(catalog.getId());
                for (CatalogItem item : catalogItems) {
                    Product prod = item.getProduct();
                    prod.setPhotos(photoRepository.findByProductId(prod.getId()));
                    auction.addProduct(prod);
                }
            }
        }
        return AuctionMapper.toDto(auction);
    }

    @Override
    public AuctionList getFututreAuctions() {
        List<AuctionDto> auctionDto = auctionRepository.findAuctionByState(State.cerrada)
                .stream().filter(a -> a.getOpenDate().after(new Date()))
                .map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }

    @Override
    public AuctionList getFututreAuctionsForUser(int id) {
        ClientDto client = clientService.getClientById(id);
        List<Category> categories = Arrays.stream(Category.values()).filter(category -> category.ordinal() <= client.getCategory().ordinal() +1).collect(Collectors.toList());
        List<AuctionDto> auctionDto = auctionRepository.findAuctionByStateAndCategoryIn(State.cerrada,categories)
                .stream().filter(a -> a.getOpenDate().after(new Date())).map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }
}
