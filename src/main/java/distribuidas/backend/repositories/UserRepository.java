package distribuidas.backend.repositories;

import distribuidas.backend.models.Client;
import distribuidas.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findFirstByEmail(String email);
}
