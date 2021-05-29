package distribuidas.backend.repositories;

import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.State;
import distribuidas.backend.models.Auction;
import distribuidas.backend.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Integer>{
    
    List<Auction> findAuctionByState(State state);
    List<Auction> findAuctionByStateAndCategoryIn(State state, List<Category> categories);
    Auction findAuctionByStateAndId(State state, int id);
}
