package playground.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import playground.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{
	
    @Query(value = "SELECT * FROM OrderDetails WHERE OrderID = :orderId", nativeQuery = true)
    List<OrderDetail> findAllByOrderID(@Param("orderId") String orderId);

}
