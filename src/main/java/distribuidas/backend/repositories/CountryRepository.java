package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
