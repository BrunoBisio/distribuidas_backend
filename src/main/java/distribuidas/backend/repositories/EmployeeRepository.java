package distribuidas.backend.repositories;

import distribuidas.backend.models.Employee;
import distribuidas.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
