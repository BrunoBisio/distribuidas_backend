package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.CatalogItem;

import java.util.List;

public interface CatalogItemRepository extends JpaRepository<CatalogItem, Integer>{
    List<CatalogItem> findByCatalogId(int catalogId);
}
