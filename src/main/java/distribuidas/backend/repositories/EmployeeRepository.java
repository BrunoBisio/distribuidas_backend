package distribuidas.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import distribuidas.backend.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
