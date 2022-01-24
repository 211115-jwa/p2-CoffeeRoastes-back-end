package p2CoffeeRoastesvanquishbackend.data;

import org.springframework.data.jpa.repository.JpaRepository;

import p2CoffeeRoastesvanquishbackend.beans.Address;



public interface AddressRepository extends JpaRepository<Address, Integer >{
	
	public Address deleteById(int id);
//	public Address create(Address newAddress);
	//public Address deleteById(Address address_id);
	public Address findAddressByUserId(int id);
	//public void findById(Address addres_id);
	
   
}
