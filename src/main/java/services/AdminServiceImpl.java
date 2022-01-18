package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import p2CoffeeRoastesvanquishbackend.beans.Plan;
import repositories.PlanRepository;

@Service
public class AdminServiceImpl implements AdminService 
{
	// the PetRepository (petDAO) is necessary for the EmployeeService,
	// therefore it is a dependency of the EmployeeService.
	private PlanRepository Planrepo;
	
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
		Plan petFromDatabase = Planrepo.findById(planToEdit.getplan_Id()).get();
		if (petFromDatabase != null) {
			Planrepo.save(planToEdit);
			return Planrepo.findById(planToEdit.getplan_Id()).get();
		}
		return null;
	}

	@Override
	public Plan getPlanById(int id) {
		return Planrepo.findById(id).get();
	}

}
