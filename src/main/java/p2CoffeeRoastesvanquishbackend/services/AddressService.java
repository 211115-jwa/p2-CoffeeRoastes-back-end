package p2CoffeeRoastesvanquishbackend.services;

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;

public interface AddressService {
	
	public int addNewAddress(Address newAddress);

	public Address getLookUpAddressByUser(int user_id);
	
	public Address deleteAddressById(Address id)throws IncorrectAddressExeption;


	

	

}
