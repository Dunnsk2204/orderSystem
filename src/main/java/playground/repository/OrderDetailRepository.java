package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import playground.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{

}
