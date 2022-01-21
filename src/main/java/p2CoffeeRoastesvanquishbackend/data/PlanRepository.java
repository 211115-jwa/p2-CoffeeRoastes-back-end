package p2CoffeeRoastesvanquishbackend.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;


import p2CoffeeRoastesvanquishbackend.beans.Plan;


public interface PlanRepository extends JpaRepository<Plan, Integer>
{

//	Set<Plan> getactivePlans();
	
}