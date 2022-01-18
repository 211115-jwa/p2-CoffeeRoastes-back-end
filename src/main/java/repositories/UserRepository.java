package repositories;

import java.util.Set;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer>
{
	public User findByUsername(String username);
}
