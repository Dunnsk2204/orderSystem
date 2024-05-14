package playground.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import playground.entity.Supplier;

// you create an interface and extends it with `CrudRepository`.
// the first object to be passed is the entity and the second is
// the type of the entity id.
public interface SupplierRepository extends JpaRepository<Supplier, String> {

//	@Modifying
//	@Transactional
//	@Query("UPDATE User u SET u.username = :newUsername WHERE u.id = :userId")
//	void updateUserById(@Param("userId") int userId, @Param("newUsername") String newUsername);

}
