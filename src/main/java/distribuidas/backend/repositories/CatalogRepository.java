package distribuidas.backend.repositories;

import distribuidas.backend.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
    Catalog findByAuctionId(int auctionId);
}
