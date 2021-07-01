package distribuidas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.enums.Admited;
import distribuidas.backend.models.Bid;

public interface BidRepository extends JpaRepository<Bid, Integer>{
    // Bid findFirstByItemOrderByIdDesc(int itemId);
    Bid findFirstByItemIdOrderByIdDesc(int itemId);
    long countByAssistantClientId(int clientId);
    long countByAssistantClientIdAndWinner(int clientId, Admited winner);
    List<Bid> findByAssistantClientId(int clientId);
    Bid findFirstByItemProductIdOrderByIdDesc(int productId);
}
