package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import playground.entity.Category;
import playground.entity.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, String>{

}
