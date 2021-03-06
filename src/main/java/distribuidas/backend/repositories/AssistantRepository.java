package distribuidas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Assistant;

public interface AssistantRepository extends JpaRepository<Assistant, Integer> {
    Assistant findByClientIdAndAuctionId(int clientId, int auctionId);
    long countByClientId(int clientId);
    List<Assistant> findByClientId(int clientId);
}
