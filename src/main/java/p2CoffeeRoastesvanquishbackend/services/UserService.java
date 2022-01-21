package p2CoffeeRoastesvanquishbackend.services;

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;

public interface UserService {
	public User register(User newUser)throws UsernameAlreadyExistsException;
	//option 1
	public User logIn(String username, String password) throws IncorrectCredentialsException;
	//option 2
//	public Address deleteUserAddressById(Address user_id) throws IncorrectAddressExeption;
//	public User deleteAddressById(Address user_id) throws IncorrectAddressExeption;
//	public Address addNewAddress(Address newAddress);
//	public Address getLookUpAddressByUser(int user_id);
//	

}
