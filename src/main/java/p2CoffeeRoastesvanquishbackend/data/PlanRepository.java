package p2CoffeeRoastesvanquishbackend.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import p2CoffeeRoastesvanquishbackend.beans.Plan;
import java.util.Optional;


@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>
{

//	Set<Plan> getactivePlans();
	
	
		public Plan findByPreferenceAndTypeAndQuantityAndGrindAndFrequency(String preference, String type, String quantity, String grind, String frequency);
		
	
}