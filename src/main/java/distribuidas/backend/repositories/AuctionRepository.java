package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Integer>{
    
}
