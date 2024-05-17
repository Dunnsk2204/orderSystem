package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import playground.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String>{
	

}
