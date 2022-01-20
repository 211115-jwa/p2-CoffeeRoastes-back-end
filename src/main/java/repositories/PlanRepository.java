package repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;


import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;

public interface PlanRepository extends JpaRepository<Plan, Integer>
{

	Set<Plan> getactivePlans();
	
}
