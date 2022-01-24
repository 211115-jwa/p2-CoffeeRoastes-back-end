package p2CoffeeRoastesvanquishbackend.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p2CoffeeRoastesvanquishbackend.beans.CustomerPlan;


@Repository
public interface CustomerPlanRepository extends JpaRepository<CustomerPlan, Integer> {

}
