package distribuidas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.enums.Category;
import distribuidas.backend.enums.State;
import distribuidas.backend.models.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Integer>{
    
    List<Auction> findAuctionByState(State state);
    List<Auction> findAuctionByStateAndCategoryIn(State state, List<Category> categories);
    Auction findAuctionByStateAndId(State state, int id);
}
