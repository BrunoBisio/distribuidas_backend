package distribuidas.backend.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import distribuidas.backend.models.Product;
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
import distribuidas.backend.services.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public AuctionList getAuctions() {
        List<AuctionDto> auctionDto = auctionRepository.findAuctionByState(State.OPEN)
                .stream().map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }

    @Override
    public AuctionList getAuctionsForUser(int id) {
        ClientDto client = clientService.getClientById(id);
        List<Category> categories = Arrays.stream(Category.values()).filter(category -> category.ordinal() <= client.getCategory().ordinal() +1).collect(Collectors.toList());
        List<AuctionDto> auctionDto = auctionRepository.findAuctionByStateAndCategoryIn(State.OPEN,categories)
                .stream().map(AuctionMapper::toDto).collect(Collectors.toList());
        return new AuctionList(auctionDto);
    }

    @Override
    public AuctionDto getAuctionById(int id) {
        // agregar fotos
        Auction auction = auctionRepository.findAuctionByStateAndId(State.OPEN,id);
        auction.getProducts().stream().forEach(prod -> prod.setPhotos(photoRepository.findByProductId(prod.getId())));
        return AuctionMapper.toDto(auction);
    }
}
