package repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import p2CoffeeRoastesvanquishbackend.beans.User;


public interface UserRepository extends JpaRepository<User, Integer>
{
	public User findByUsername(String username);
	public User findById(int id);
}
