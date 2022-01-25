package p2CoffeeRoastesvanquishbackend.services;




import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.data.PlanRepository;

import p2CoffeeRoastesvanquishbackend.beans.CustomerPlan;

import p2CoffeeRoastesvanquishbackend.data.CustomerPlanRepository;

import p2CoffeeRoastesvanquishbackend.data.UserRepository;

import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;
import p2CoffeeRoastesvanquishbackend.exceptions.customerplandoesnotexist;


@Service
public class UserServiceImpl implements UserService  {
	private UserRepository userRepo;
	private PlanRepository planRepo;
  	private CustomerPlanRepository Customerplanrepo;
	
	

	
	@Autowired
	public UserServiceImpl(UserRepository userRepo, CustomerPlanRepository Customerplanrepo, PlanRepository planRepo) {
		this.userRepo = userRepo;
		this.Customerplanrepo = Customerplanrepo;
   	this.planRepo = planRepo;
   	

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
	public Plan getPlan(String preference, String type, String quantity, String grind, String frequency) {
		Plan planFromDatabase = planRepo.findByPreferenceAndTypeAndQuantityAndGrindAndFrequency(preference, type, quantity, grind, frequency);

	return planFromDatabase;

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
		
		if(targetcustomerplan.getActive().equals("True"))
		{
			targetcustomerplan.setActive("False");
		}
		else
		{
			targetcustomerplan.setActive("True");
		}
		return targetcustomerplan;
	}
	
	//@SuppressWarnings("deprecation")
	@Override
	public Set<CustomerPlan> getallactiveplans(int user_id)
	{
		Set<CustomerPlan> customerplans = Customerplanrepo.findByActiveAndUser("True", userRepo.findById(user_id));
		/*for(int i=0; i<Customerplanrepo.count(); i++)
		{
			if(Customerplanrepo.getOne(i).getUser().getId()==user_id && Customerplanrepo.getOne(i).getActive_plan()=="True")
			{
				customerplans.add(Customerplanrepo.getOne(i));
			}
		}*/
		
		return customerplans;
	}


	@Override
	public User getUserById(int id) {
		Optional<User> user = Optional.ofNullable(userRepo.findById(id));
		if (user.isPresent()) return user.get();
		else return null;

	}


	@Override
	public User updateUser(User userToUpdate) {
		if (userRepo.existsById(userToUpdate.getId())) {
			userRepo.save(userToUpdate);
			userToUpdate = userRepo.findById(userToUpdate.getId());
			return userToUpdate;
		}
		return null;

	}
	

	
	
	

	
	
	
	
}
