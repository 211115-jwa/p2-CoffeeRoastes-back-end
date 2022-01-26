package p2CoffeeRoastesvanquishbackend.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p2CoffeeRoastesvanquishbackend.data.AddressRepository;
import p2CoffeeRoastesvanquishbackend.beans.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer >{
	
	public Address deleteById(int id);
//public Address addAddressWhereUserIs(Address newAddress);
	public Set<Address> findByUserId(int id);
	public Address findById(int id);
	
	

	
	
   
}
