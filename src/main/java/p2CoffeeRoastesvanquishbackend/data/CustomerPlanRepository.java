package p2CoffeeRoastesvanquishbackend.data;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p2CoffeeRoastesvanquishbackend.beans.CustomerPlan;
import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;


@Repository
public interface CustomerPlanRepository extends JpaRepository<CustomerPlan, Integer> 
{
	public Set<CustomerPlan> getallactiveplansbyuserid(String active_plan, User user);

}
