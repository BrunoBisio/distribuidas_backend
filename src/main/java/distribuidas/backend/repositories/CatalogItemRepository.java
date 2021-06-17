package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.enums.Admited;
import distribuidas.backend.models.CatalogItem;

import java.util.List;

public interface CatalogItemRepository extends JpaRepository<CatalogItem, Integer>{
    List<CatalogItem> findByCatalogId(int catalogId);
    // PRODUCTOS SUBASTADOS
    // SELECT P.* FROM productos P JOIN duenios D ON P.duenio = D.identificador 
    // JOIN itemsCatalogo IC ON P.identificador = IC.producto
    // WHERE D.identificador = ?1 AND IC.subastado = 'si'
    List<CatalogItem> findByProductOwnerIdAndAuctioned(int ownerId, Admited auctioned);
}
