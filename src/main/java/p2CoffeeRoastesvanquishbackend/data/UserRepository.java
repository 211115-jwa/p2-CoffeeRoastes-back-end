package p2CoffeeRoastesvanquishbackend.data;

import org.springframework.data.jpa.repository.JpaRepository;

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.beans.User;

import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
	public User findById(int id);
	public static User deleteById(Address id) {
   
		return null;
	}
//	public Address findAddressByUserId(String string);
//	public Address create(Address newAddress);
	
	
}
