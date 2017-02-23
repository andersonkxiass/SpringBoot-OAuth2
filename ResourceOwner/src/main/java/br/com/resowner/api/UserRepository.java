package br.com.resowner.api;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByLogin(String login);
	User findByLoginAndPassword(String login, String password);
	
	@Query("SELECT u FROM User u WHERE u.login = :login and u.password = :password")
	public User find(@Param("login") String login, @Param("password") String password);
}