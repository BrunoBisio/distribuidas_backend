package distribuidas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.enums.Status;
import distribuidas.backend.models.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>{
    List<PaymentMethod> findByClientIdAndStatus(int clientId, Status status);
    boolean existsByClientId(int clientId);
}
