package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Assistant;

public interface AssistantRepository extends JpaRepository<Assistant, Integer> {
    
    Assistant findByClientIdAndAuctionId(int clientId, int auctionId);
}
