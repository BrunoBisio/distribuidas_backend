package distribuidas.backend.services.impl;

import distribuidas.backend.dtos.AuctionDto;
import distribuidas.backend.dtos.AuctionList;
import distribuidas.backend.dtos.ClientDto;
import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.State;
import distribuidas.backend.mappers.AuctionMapper;
import distribuidas.backend.models.Client;
import distribuidas.backend.repositories.AuctionRepository;
import distribuidas.backend.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private ClientService clientService;
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
        return AuctionMapper.toDto(auctionRepository.findAuctionByStateAndId(State.OPEN,id));
    }
}
