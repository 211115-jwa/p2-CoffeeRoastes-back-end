package services;


import p2CoffeeRoastesvanquishbackend.beans.Plan;

public interface AdminService {

	public int addNewPlan(Plan newPlan);
	public Plan editPlan(Plan planToEdit);
	public Plan getPlanById(int id);
}
