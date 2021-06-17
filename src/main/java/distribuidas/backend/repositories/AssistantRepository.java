package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import distribuidas.backend.models.Assistant;

public interface AssistantRepository extends JpaRepository<Assistant, Integer> {
    
    Assistant findByClientIdAndAuctionId(int clientId, int auctionId);
    
    long countByClientId(int clientId);

    @Query(value="SELECT count(asistente) as cant_pujos " +
        "FROM asistentes a " +
        "JOIN pujos p ON a.identificador = p.asistente " +
        "WHERE cliente = ?1", nativeQuery = true)
    long countbyBidsCreated(int clientId);

    @Query(value = "SELECT count(asistente) as cant_ganadas " +
        "FROM asistentes a " +
        "JOIN pujos p ON a.identificador = p.asistente " +
        "WHERE cliente = ?1 AND ganador = 'si'", nativeQuery = true)
    long countByAuctionsWon(int clientId);
}
