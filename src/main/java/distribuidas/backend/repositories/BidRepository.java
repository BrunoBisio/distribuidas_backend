package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{
    Bid findFirstByItemOrderByBidIdDesc(int itemId);
}
