package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.CatalogItem;

public interface CatalogItemRepository extends JpaRepository<CatalogItem, Integer>{
    
}
