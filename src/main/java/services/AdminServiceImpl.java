package services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;
import repositories.PlanRepository;
import repositories.UserRepository;

@Service
public class AdminServiceImpl implements AdminService 
{
	// the PetRepository (petDAO) is necessary for the EmployeeService,
	// therefore it is a dependency of the EmployeeService.
	private PlanRepository Planrepo;
	private UserRepository Userrepo;
	
	// constructor injection
	@Autowired
	public AdminServiceImpl(PlanRepository Planrepo) {
		this.Planrepo=Planrepo;
	}

	@Override
	@Transactional
	public int addNewPlan(Plan newPlan) {
		//return petDao.create(newPet);
		return Planrepo.save(newPlan).getplan_Id();
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
		return user.getPlans();
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

	@Override
	public Set<Plan> getActivePlans() 
	{
		return Planrepo.getactivePlans();
	}
	
	
}
