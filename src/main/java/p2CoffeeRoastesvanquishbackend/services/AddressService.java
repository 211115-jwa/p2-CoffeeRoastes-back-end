package p2CoffeeRoastesvanquishbackend.services;

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;

public interface AddressService {
	
	public Address addNewAddress(Address newAddress);

	public Address getLookUpAddressByUser(int user_id);
	
	public Address deleteAddressById(int id)throws IncorrectAddressExeption;


	

	

}
