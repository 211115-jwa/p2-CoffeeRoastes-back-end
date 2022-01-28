package p2CoffeeRoastesvanquishbackend.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import p2CoffeeRoastesvanquishbackend.beans.CreditCard;

import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.data.PlanRepository;

import p2CoffeeRoastesvanquishbackend.beans.CustomerPlan;
import p2CoffeeRoastesvanquishbackend.data.CreditCardRepository;
import p2CoffeeRoastesvanquishbackend.data.CustomerPlanRepository;

import p2CoffeeRoastesvanquishbackend.data.UserRepository;
import p2CoffeeRoastesvanquishbackend.exceptions.CustomerDoesNotExistException;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;
import p2CoffeeRoastesvanquishbackend.exceptions.customerplandoesnotexist;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepo;
	private PlanRepository planRepo;
	private CustomerPlanRepository Customerplanrepo;
	private CreditCardRepository creditRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo, CustomerPlanRepository Customerplanrepo, PlanRepository planRepo,  CreditCardRepository creditRepo) {
		this.userRepo = userRepo;
		this.Customerplanrepo = Customerplanrepo;
		this.planRepo = planRepo;
		this.creditRepo = creditRepo;

	}

	@Override
	@Transactional
	public User register(User newUser) throws UsernameAlreadyExistsException {
		int newId = userRepo.save(newUser).getId();
		if (newId > 0) {
			newUser.setId(newId);
			return newUser;
		} else if (newId == -1) {
			throw new UsernameAlreadyExistsException();
		}
		return null;
	}

	@Override
	public User logIn(String username, String password) throws IncorrectCredentialsException {
		User userFromDatabase = userRepo.findByUsername(username);
		if (userFromDatabase != null && userFromDatabase.getPassword().equals(password)) {
			return userFromDatabase;
		} else {
			throw new IncorrectCredentialsException();
		}
	}



	@Override
	public CustomerPlan deletecustomerPlanbyID(int customerplanID) throws customerplandoesnotexist {
		CustomerPlan customerplan = Customerplanrepo.getById(customerplanID);
		Customerplanrepo.delete(customerplan);
		if (customerplan != null) {
			return customerplan;
		} else {
			throw new customerplandoesnotexist();
		}
	}

	@Override
	public Plan getPlan(String preference, String type, String quantity, String grind, String frequency) {
		Plan planFromDatabase = planRepo.findByPreferenceAndTypeAndQuantityAndGrindAndFrequency(preference, type,
				quantity, grind, frequency);

		return planFromDatabase;

	}

	@Override
	public CustomerPlan CreateNewPlan(CustomerPlan customerplan) {
		CustomerPlan plan = Customerplanrepo.save(customerplan);
		if (plan != null) {
			return plan;
		} else {
			return null;
		}

	}

	@Override
	public CustomerPlan toggle(int customerplanID) {
		CustomerPlan targetcustomerplan = Customerplanrepo.getById(customerplanID);

		if (targetcustomerplan.getActive_plan().equals("True")) {
			targetcustomerplan.setActive_plan("False");
		} else {
			targetcustomerplan.setActive_plan("True");
		}
		return targetcustomerplan;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Set<CustomerPlan> getallactiveplans(int user_id) {
		Set<CustomerPlan> customerplans = new HashSet<CustomerPlan>();
		for (int i = 0; i < Customerplanrepo.count(); i++) {
			if (Customerplanrepo.getOne(i).getUser().getId() == user_id
					&& Customerplanrepo.getOne(i).getActive_plan() == "True") {
		
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
	


	
	@Override
	public User getUserById(int id) {
		Optional<User> user = Optional.ofNullable(userRepo.findById(id));
		if (user.isPresent()) return user.get();
		else return null;

	}



	public User updateUser(User userToUpdate) {
		if (userRepo.existsById(userToUpdate.getId())) {
			userRepo.save(userToUpdate);
			userToUpdate = userRepo.findById(userToUpdate.getId());
			return userToUpdate;
		}
		return null;

	}

	@Override
	public Set<CustomerPlan> getCustomerPlansByName(String name) {
		Set<CustomerPlan>  plansByUser = Customerplanrepo.findByUserUsername(name);
		return plansByUser;
	}
}
