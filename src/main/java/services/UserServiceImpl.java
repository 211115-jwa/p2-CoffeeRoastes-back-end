package services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;
import repositories.PlanRepository;
import repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService 
{
	private UserRepository userRepo;
	private PlanRepository planRepo;
	
	// constructor injection
	@Autowired
	public UserServiceImpl(UserRepository personRepo,
			PlanRepository petRepo) 
	{
		this.userRepo = personRepo;
		this.planRepo = planRepo;
	}

	@Override
	@Transactional
	public User register(User newUser) 
	{
		int newId = userRepo.save(newUser).getId();
		if (newId > 0) {
			newUser.setId(newId);
			return newUser;
		}
		return null;
	}

	@Override
	public User logIn(String username, String password) 
	{
		User personFromDatabase = userRepo.findByUsername(username);
		if (personFromDatabase != null && personFromDatabase.getPassword().equals(password)) {
			return personFromDatabase;
		}
		return personFromDatabase;//probably something wrong here
	}
	
	@Override
	public User getUserById(int id) 
	{
		return userRepo.findById(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public User updateUser(User userToUpdate) {
		if (userRepo.existsById(userToUpdate.getId())) {
			userRepo.save(userToUpdate);
			userToUpdate = userRepo.findById(userToUpdate.getId());
			return userToUpdate;
		}
		return null;
	}

	@Override
	@Transactional
	public User choosePlan(int planId, User user) 
	{
		//Plan chosenPlan = planRepo.findById(planId).get();
		Plan plan = planRepo.getById(planId);
		if (plan!=null) {
			Set<Plan> plans = user.getPlans();
			plans.add(planRepo.getById(planId));
			user.setPlans(plans);
			
			//planRepo.save(chosenPlan);
			//this.updateUser(user);
			//personRepo.save(newOwner);
			return user;
		}
		return user;
	}

	@Override
	public Set<Plan> viewPlans() {
		return userRepo.getById(null).getPlans();
	}

	@Override
	public Plan getPlanById(int id) 
	{
		return planRepo.getById(id);
	}
}
