package services;


import java.util.Set;

import p2CoffeeRoastesvanquishbackend.beans.Plan;

public interface AdminService {

	public int addNewPlan(Plan newPlan);
	public Plan editPlan(Plan planToEdit);
	public Plan getPlanById(int id);
	public Set<Plan> getPlansByUserId(int id);
	public Plan togglePlan(int id);
	public void deleteplan(Plan plan);
	public Set<Plan> getActivePlans(); 
}
