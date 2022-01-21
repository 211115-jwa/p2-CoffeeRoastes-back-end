package p2CoffeeRoastesvanquishbackend.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.data.PlanRepository;
import p2CoffeeRoastesvanquishbackend.data.UserRepository;


@Service
public class AdminServiceImpl implements AdminService 
{
	// the PetRepository (petDAO) is necessary for the EmployeeService,
	// therefore it is a dependency of the EmployeeService.
	private PlanRepository Planrepo;
	private UserRepository Userrepo;

	// constructor injection
	@Autowired
	public AdminServiceImpl(UserRepository userRepo, PlanRepository Planrepo) {
		this.Userrepo = userRepo;
		this.Planrepo = Planrepo;
	}
	
	
	@Override
	public int addNewPlan(Plan newPlan) {
		return 0;
		}
	
	
	@Override
	@Transactional
	public Plan editPlan(Plan planToEdit) {
		Plan planFromDatabase = Planrepo.findById(planToEdit.getplan_Id()).get();
		if (planFromDatabase != null) {
			Planrepo.save(planToEdit);
			return Planrepo.findById(planToEdit.getplan_Id()).get();
		}
		return null;
	}

	@Override
	public Plan getPlanById(int id) 
	{
		return Planrepo.findById(id).get();
	}

	@Override
	public Set<Plan> getPlansByUserId(int id) 
	{
		User user= Userrepo.findById(id);
//		return user.getPlans();
		return null;
	}


	@Override
	public Plan togglePlan(int id) 
	{
		return Planrepo.findById(id).get();
	}

	@Override
	public void deleteplan(Plan plan) 
	{
		Planrepo.delete(plan);
		return;
	}

//	@Override
//	public Set<Plan> getActivePlans() 
//	{
//		return Planrepo.getactivePlans();
//	}


}