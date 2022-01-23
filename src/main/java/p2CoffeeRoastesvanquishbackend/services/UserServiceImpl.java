package p2CoffeeRoastesvanquishbackend.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.beans.CustomerPlan;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.data.CustomerPlanRepository;
import p2CoffeeRoastesvanquishbackend.data.UserRepository;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;
import p2CoffeeRoastesvanquishbackend.exceptions.customerplandoesnotexist;


@Service
public class UserServiceImpl implements UserService  {
	private UserRepository userRepo;
	private CustomerPlanRepository Customerplanrepo;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo, CustomerPlanRepository Customerplanrepo) {
		this.userRepo = userRepo;
		this.Customerplanrepo = Customerplanrepo;
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
	public CustomerPlan getcustomerPlanbyID(int customerplanID) throws customerplandoesnotexist
	{
			CustomerPlan customerplan = Customerplanrepo.getById(customerplanID);
			if (customerplan != null) {
				return customerplan;
			}else {
				throw new customerplandoesnotexist();
			}
	}
	
	@Override
	public CustomerPlan deletecustomerPlanbyID(int customerplanID) throws customerplandoesnotexist
	{
			CustomerPlan customerplan = Customerplanrepo.getById(customerplanID);
			Customerplanrepo.delete(customerplan);
			if (customerplan != null) {
				return customerplan;
			}else {
				throw new customerplandoesnotexist();
			}
	}

	@Override
	public CustomerPlan CreateNewPlan(CustomerPlan customerplan)
	{
		Customerplanrepo.save(customerplan);
		return customerplan;
	}
	
	@Override
	public CustomerPlan toggle(int customerplanID)
	{
		CustomerPlan targetcustomerplan = Customerplanrepo.getById(customerplanID);
		
		if(targetcustomerplan.getActive_plan().equals("True"))
		{
			targetcustomerplan.setActive_plan("False");
		}
		else
		{
			targetcustomerplan.setActive_plan("True");
		}
		return targetcustomerplan;
	}
	
	@Override
	public Set<CustomerPlan> getallactiveplans(int user_id)
	{
		Set<CustomerPlan> customerplans = new HashSet<CustomerPlan>(); 
		for(int i=0; i<Customerplanrepo.count(); i++)
		{
			if(Customerplanrepo.getOne(i).getUser().getId()==user_id && Customerplanrepo.getOne(i).getActive_plan()=="True")
			{
				customerplans.add(Customerplanrepo.getOne(i));
			}
		}
		return customerplans;
	}
	
	
//	//option 1
//	@Override
//	public Address deleteUserAddressById(Address user_id) throws IncorrectAddressExeption {
//		
//		Address DeleteUserAddressFromDatabase  = userRepo.findAddressByUserId(" 11006 SE 68TH ST APT 303");
//		if(DeleteUserAddressFromDatabase != null) {
//			return DeleteUserAddressFromDatabase;
//		
//		}else {
//			
//			throw new IncorrectAddressExeption();
//		}
//		
//	}
	//option 2
//	@Override 
//	  
//	  public User deleteAddressById(Address user_id) throws IncorrectAddressExeption {
//	  
//	  User DeleteAddressFromDatabase = userRepo.getById(user_id.getAddress_id());
//	  if(DeleteAddressFromDatabase != null) {
//		  
//		  return DeleteAddressFromDatabase;
//	  
//	  }else {
//	  
//	  throw new IncorrectAddressExeption(); }
//	  
//	  }
//	 
//	 
//	
//
//	@Override
//	public Address addNewAddress(Address newAddress) {
//		return newAddress;
//		// there is no create for User. Create an Address Repo
////		return userRepo.create(newAddress);
//		
//	}


//	@Override
//	public Address getLookUpAddressByUser(int user_id) {
//	Address UserByAddress = userRepo.findAddressByUserId("User Address exist");
//	  if(UserByAddress != null ){
//		userRepo.findById(user_id);
//		 }
//	return UserByAddress;
//	}

	
	
	
	
}
