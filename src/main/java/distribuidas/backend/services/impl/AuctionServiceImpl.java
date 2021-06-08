package distribuidas.backend.services.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.AuctionDto;
import distribuidas.backend.dtos.AuctionList;
import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.State;
import distribuidas.backend.mappers.AuctionMapper;
import distribuidas.backend.models.Auction;
import distribuidas.backend.models.Catalog;
import distribuidas.backend.models.CatalogItem;
import distribuidas.backend.models.Product;
import distribuidas.backend.repositories.AuctionRepository;
import distribuidas.backend.repositories.CatalogItemRepository;
import distribuidas.backend.repositories.CatalogRepository;
import distribuidas.backend.repositories.PhotoRepository;
import distribuidas.backend.services.IAuctionService;

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
                .stream().map(this::setProducts).map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }

    @Override
    public AuctionList getAuctionsForUser(int id) {
        ClientDto client = clientService.getClientById(id);
        List<Category> categories = Arrays.stream(Category.values())
            .filter(category -> category.ordinal() <= client.getCategory().ordinal() +1)
            .collect(Collectors.toList());
        List<AuctionDto> auctionDto = auctionRepository.findAuctionByStateAndCategoryIn(State.abierta,categories)
                .stream().map(this::setProducts).map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }

    @Override
    public AuctionList getFututreAuctions() {
        List<AuctionDto> auctionDto = auctionRepository.findAuctionByState(State.cerrada)
                .stream().filter(a -> a.getOpenDate().after(new Date()))
                .map(this::setProducts).map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }

    @Override
    public AuctionList getFututreAuctionsForUser(int id) {
        ClientDto client = clientService.getClientById(id);
        List<Category> categories = Arrays.stream(Category.values())
            .filter(category -> category.ordinal() <= client.getCategory().ordinal() +1)
            .collect(Collectors.toList());
        List<AuctionDto> auctionDto = auctionRepository.findAuctionByStateAndCategoryIn(State.cerrada,categories)
                .stream().filter(a -> a.getOpenDate().after(new Date()))
                .map(this::setProducts).map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }

    @Override
    public AuctionDto getAuctionById(int id) {
        Auction auction = auctionRepository.findById(id).get();
        auction = setProducts(auction);
        return AuctionMapper.toDto(auction);
    }

    private Auction setProducts(Auction auction) {
        if (auction != null) {
            Catalog catalog = catalogRepository.findByAuctionId(auction.getId());
            if (catalog != null) {
                List<Product> products = catalogItemRepository.findByCatalogId(catalog.getId())
                    .stream().map(this::setProductPrice).map(CatalogItem::getProduct)
                    .map(this::setPhoto).collect(Collectors.toList());
                auction.setProducts(products);
            }
        }
        return auction;
    }

    private CatalogItem setProductPrice(CatalogItem item) {
        item.getProduct().setPrice(item.getBasePrice());
        return item;
    }

    private Product setPhoto(Product product) {
        product.setPhotos(photoRepository.findByProductId(product.getId()));
        return product;
    }
}
