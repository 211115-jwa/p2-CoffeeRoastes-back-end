package p2CoffeeRoastesvanquishbackend.services;

import java.util.Set;

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;

public interface AddressService {
	
	public Address addNewAddress(Address newAddress);

	public Set<Address> getLookUpAddressByUser(int user_id);
	
	public Address deleteAddressById(int id)throws IncorrectAddressExeption;
	
	public Address getAddressById(int id);


	

	

}
