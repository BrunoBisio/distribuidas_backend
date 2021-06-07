package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findFirstByEmail(String email);
}
