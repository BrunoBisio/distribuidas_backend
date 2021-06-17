package distribuidas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.AuctionRegistry;

public interface AuctionRegistryRepository extends JpaRepository<AuctionRegistry, Integer> {
    List<AuctionRegistry> findByClientId(int clientId);
}
