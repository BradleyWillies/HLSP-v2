package DC3160.HLSP.v2.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import DC3160.HLSP.v2.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT id FROM user WHERE email = :email")
	Integer getIdForEmail(@Param("email") String email);

	@Query("SELECT id FROM user WHERE email = :email AND password = :password")
	Integer getIdForEmailAndPass(@Param("email") String email, @Param("password") String password);
}
