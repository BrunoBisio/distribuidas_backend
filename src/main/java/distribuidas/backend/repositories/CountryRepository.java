package distribuidas.backend.repositories;

import distribuidas.backend.models.Country;
import distribuidas.backend.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
