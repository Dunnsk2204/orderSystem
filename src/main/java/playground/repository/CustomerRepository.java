package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import playground.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{

}
