package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import playground.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{

}

