package p2CoffeeRoastesvanquishbackend.data;

import org.springframework.data.jpa.repository.JpaRepository;

import p2CoffeeRoastesvanquishbackend.beans.Address;


public interface AddressRepository extends JpaRepository<Address, Integer >{

}
