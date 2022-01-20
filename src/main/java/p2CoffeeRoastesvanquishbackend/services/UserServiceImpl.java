package p2CoffeeRoastesvanquishbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.data.UserRepository;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;
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


	//option 1
	@Override
	public Address deleteUserAddressById(Address user_id) throws IncorrectAddressExeption {
		
		Address DeleteUserAddressFromDatabase  = userRepo.findAddressByUserId(" 11006 SE 68TH ST APT 303");
		if(DeleteUserAddressFromDatabase != null) {
			return DeleteUserAddressFromDatabase;
		
		}else {
			
			throw new IncorrectAddressExeption();
		}
		
	}
	//option 2
	@Override 
	  
	  public User deleteAddressById(Address user_id) throws IncorrectAddressExeption {
	  
	  User DeleteAddressFromDatabase = userRepo.getById(user_id.getAddress_id());
	  if(DeleteAddressFromDatabase != null) {
		  
		  return DeleteAddressFromDatabase;
	  
	  }else {
	  
	  throw new IncorrectAddressExeption(); }
	  
	  }
	 
	 
	

	@Override
	public Address addNewAddress(Address newAddress) {
		return userRepo.create(newAddress);
		
	}


	@Override
	public Address getLookUpAddressByUser(int user_id) {
	Address UserByAddress = userRepo.findAddressByUserId("User Address exist");
	  if(UserByAddress != null ){
		userRepo.findById(user_id);
		 }
	return UserByAddress;
	}

	
	
	
	
}
