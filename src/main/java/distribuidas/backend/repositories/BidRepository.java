package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Bid;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer>{
    List<Bid> findByItemIdOrderByBidIdDesc(int itemId);
}
