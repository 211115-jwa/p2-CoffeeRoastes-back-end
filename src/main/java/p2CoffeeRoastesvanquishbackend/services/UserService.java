package p2CoffeeRoastesvanquishbackend.services;

import p2CoffeeRoastesvanquishbackend.beans.CreditCard;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;

public interface UserService {
	public User register(User newUser)throws UsernameAlreadyExistsException;
	public User logIn(String username, String password) throws IncorrectCredentialsException;
	public int addNewCreditCard(CreditCard newCreditCard);
	public User getCreditCardByUser(String creditCardUser);


}
