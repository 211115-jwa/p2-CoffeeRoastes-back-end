package p2CoffeeRoastesvanquishbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import p2CoffeeRoastesvanquishbackend.beans.CreditCard;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.data.UserRepository;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;


@Service
public class UserServiceImpl implements UserService  {
	private UserRepository userRepo;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	

	@Override
	@Transactional
	public User register(User newUser) throws UsernameAlreadyExistsException{
		int newId = userRepo.save(newUser).getId();	
		if (newId> 0) {
			newUser.setId(newId);
			return newUser;
		} else if (newId == -1) {
			throw new UsernameAlreadyExistsException();
		}
		return null;
	}

	@Override
	public User logIn(String username, String password) throws IncorrectCredentialsException{
			User userFromDatabase = userRepo.findByUsername(username);
			if (userFromDatabase != null && userFromDatabase.getPassword().equals(password)) {
				return userFromDatabase;
			}else {
				throw new IncorrectCredentialsException();
			}
		
	}


	@Override
	@Transactional
	public int addNewCreditCard(CreditCard newCreditCard) {
		return creditCardRepo.save(newCreditCard).getCreditCardId();
	}

	@Override
	public User getCreditCardByUser(String creditCardUser) {
		User UserByCreditCard = creditCardRepo.findCreditCardByUserId(user_id);
		if (UserByCreditCard != null) {
			return null;
		}
		return UserByCreditCard;
	}

}
