package distribuidas.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import distribuidas.backend.dtos.AuctionRegistryDto;
import distribuidas.backend.mappers.AuctionRegistryMapper;
import distribuidas.backend.repositories.AuctionRegistryRepository;
import distribuidas.backend.services.IAuctionRegistryService;

@Service
public class AuctionRegistryServiceImpl implements IAuctionRegistryService {

    @Autowired
    private AuctionRegistryRepository arRepository;

    @Override
    public List<AuctionRegistryDto> getBoughtProducts(int clientId) {
        return arRepository.findbyClientId(clientId).stream()
            .map(AuctionRegistryMapper::toDto).collect(Collectors.toList());
    }
    
}
