package distribuidas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.enums.Admited;
import distribuidas.backend.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // PRODUCTOS EN SUBASTAS PENDIENTES
    /*@Query(value = "SELECT IC.* " +
        "FROM productos P " +
        "JOIN duenios D ON P.duenio = D.identificador " +
        "JOIN itemsCatalogo IC ON P.identificador = IC.producto " +
        "JOIN catalogos C ON IC.catalogo = C.identificador " +
        "JOIN subastas S ON  C.subasta = S.identificador " +
        "WHERE D.identificador = ?1 AND S.estado = 'cerrada' AND IC.subastado = 'no'", nativeQuery = true)
    List<CatalogItem> findPendingAuctionProducts(int ownerId);*/

    // PRODUCTOS NO ACEPTADOS
    // SELECT P.* FROM productos P JOIN duenios D ON P.duenio = D.identificador 
    // WHERE D.identificador = ?1 AND P.aprobado = 'no'
    List<Product> findByOwnerIdAndApproved(int ownerId, Admited approved);
    
    // SELECT COUNT(*) AS cant_prod FROM productos WHERE DUENIO = ?1 AND aprobado = 'si'
    long countByOwnerIdAndApproved(int ownerId, Admited approved); // revisar porq trae 4

    Product findByIdAndOwnerId(int id, int ownerId);
}
