package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import playground.entity.Product;

public interface ProductRepository extends JpaRepository<Product,String>{

}
