package distribuidas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.AuctionRegistry;
import distribuidas.backend.models.Product;

public interface AuctionRegistryRepository extends JpaRepository<AuctionRegistry, Integer> {
    List<AuctionRegistry> findByClientId(int clientId);
    List<AuctionRegistry> findByClientIdAndProductIn(int clientId, List<Product> products);
    List<AuctionRegistry> findByOwnerIdAndProductIn(int ownerId, List<Product> products);
}
