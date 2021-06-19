package distribuidas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import distribuidas.backend.enums.Admited;
import distribuidas.backend.enums.State;
import distribuidas.backend.models.CatalogItem;

public interface CatalogItemRepository extends JpaRepository<CatalogItem, Integer>{
    List<CatalogItem> findByCatalogId(int catalogId);
    // PRODUCTOS SUBASTADOS
    // SELECT P.* FROM productos P JOIN duenios D ON P.duenio = D.identificador 
    // JOIN itemsCatalogo IC ON P.identificador = IC.producto
    // WHERE D.identificador = ?1 AND IC.subastado = 'si'
    List<CatalogItem> findByProductOwnerIdAndAuctioned(int ownerId, Admited auctioned);
    /*
        SELECT nombreDeProducto, precioBase, rs.importe, ic.comision, rs.comision
        FROM productos p
        JOIN itemsCatalogo ic ON ic.producto = p.identificador
        JOIN catalogos c ON c.identificador = ic.catalogo
        JOIN registroDeSubasta rs ON rs.subasta = c.subasta
        WHERE p.duenio = ?1 AND aprobado = 'si';
    */
    List<CatalogItem> findByProductOwnerIdAndProductApproved(int ownerId, Admited approved);

    List<CatalogItem> findByProductOwnerIdAndCatalogAuctionState(int ownerId, State state);

    @Query(value = "SELECT IC.* " +
        "FROM productos P " +
        "JOIN duenios D ON P.duenio = D.identificador " +
        "JOIN itemsCatalogo IC ON P.identificador = IC.producto " +
        "JOIN catalogos C ON IC.catalogo = C.identificador " +
        "JOIN subastas S ON  C.subasta = S.identificador " +
        "WHERE D.identificador = ?1 AND S.estado = 'cerrada' AND IC.subastado = 'no'", nativeQuery = true)
    List<CatalogItem> findPendingAuctionProducts(int ownerId);
}
