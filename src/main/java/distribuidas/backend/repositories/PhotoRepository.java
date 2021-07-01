package distribuidas.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findByProductId(int productId);
    void deleteByProductId(int productId);
}
