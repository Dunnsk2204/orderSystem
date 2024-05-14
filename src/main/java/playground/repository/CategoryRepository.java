package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import playground.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, String>{

}
