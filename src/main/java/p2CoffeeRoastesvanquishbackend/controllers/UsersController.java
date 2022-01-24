package p2CoffeeRoastesvanquishbackend.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.beans.CustomerPlan;
import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;
//import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;
import p2CoffeeRoastesvanquishbackend.exceptions.customerplandoesnotexist;
import p2CoffeeRoastesvanquishbackend.services.AdminService;
import p2CoffeeRoastesvanquishbackend.services.UserService;

@RestController
@RequestMapping(path="/users")
@CrossOrigin(origins="http://localhost:4200")

public class UsersController {
	
	private UserService userServ;
	private AdminService adminServ;
	
	@Autowired
	public UsersController(UserService userServ) {
		this.userServ=userServ;
	}
	
	
	public ResponseEntity<Map<String,Integer>> register(@RequestBody User newUser) {
		try {
			newUser = userServ.register(newUser);
			Map<String, Integer> newIdMap = new HashMap<>();
			newIdMap.put("generatedId", newUser.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(newIdMap);
		} catch (UsernameAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	// POST to /users/auth
	@PostMapping(path="/auth")
	public ResponseEntity<String> logIn(@RequestBody Map<String, String> credentials) {
		String username = credentials.get("username");
		String password = credentials.get("password");
		
		try {
			User person = userServ.logIn(username, password);
			String token = Integer.toString(person.getId());
			return ResponseEntity.ok(token);
		} catch (IncorrectCredentialsException e) {
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping(path="/getplan")
	public ResponseEntity<Plan> getPlan(@RequestBody Map<String, String> input) {
		String preference = input.get("preference");
		String type = input.get("type");
		String quantity = input.get("quantity");
		String grind = input.get("grind");
		String frequency= input.get("frequency");
		
		try {
			Plan plan = userServ.getPlan(preference, type, quantity, grind, frequency);
//			String token = Integer.toString(person.getId());
			return ResponseEntity.ok(plan);
		} 
		finally {

		}
//		catch (IncorrectCredentialsException e) {
//			return ResponseEntity.notFound().build();
//		}
	}
	
	

	@PostMapping(path="/createPlan")
	public ResponseEntity<CustomerPlan> logIn(@RequestBody CustomerPlan newPlan) 
	{
		userServ.CreateNewPlan(newPlan);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPlan);
	}

	
	// Get to /users/getCustomerPlans
	@GetMapping(path="/getCustomerPlans/{user_Id}")
	public ResponseEntity<Set<Plan>> getCustomerPlan(@PathVariable int user_Id) 
	{
		Set<Plan> plans= adminServ.getPlansByUserId(user_Id);
		return ResponseEntity.status(HttpStatus.CREATED).body(plans);
	}
	
	// Get to /users/getcustomerPlanbyID
	@GetMapping(path="/getcustomerPlanbyID/{customer_plan_id}")
	public ResponseEntity<CustomerPlan> getPlanbyID(@PathVariable int customer_plan_id) throws customerplandoesnotexist 
	{
		CustomerPlan customerplan= userServ.getcustomerPlanbyID(customer_plan_id);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerplan);
	}
	
	// Delete to /users/deletePlanbyID
	@DeleteMapping(path="/deletePlanbyID/{customer_plan_id}")
	public ResponseEntity<Integer> deletePlanbyID(@PathVariable int customer_plan_id) throws customerplandoesnotexist 
	{
		userServ.deletecustomerPlanbyID(customer_plan_id);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer_plan_id);
	}
	
	// Put to /users/togglecustomerplan
	@PutMapping(path="/togglecustomerplan/{customer_plan_id}")
	public ResponseEntity<CustomerPlan> togglecustomerplan(@PathVariable int customer_plan_id) throws customerplandoesnotexist 
	{
		CustomerPlan customerplan= userServ.toggle(customer_plan_id);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerplan);
	}
	
	// Get to /users/getallactiveplans
	@GetMapping(path="/getallactiveplans/{user_id}")
	public ResponseEntity<Set<CustomerPlan>> getallactiveplans(@PathVariable int user_id)
	{
		Set<CustomerPlan> activecustomerplans= userServ.getallactiveplans(user_id);
		return ResponseEntity.status(HttpStatus.CREATED).body(activecustomerplans);
	}
	
	
//	@GetMapping(path="/getPlanbyID/{plan_Id}")
//	public ResponseEntity<Plan> getPlanbyID(@PathVariable int plan_Id) 
//	{
//		Plan plan= userServ.getPlanById(plan_Id);
//		return ResponseEntity.status(HttpStatus.CREATED).body(plan);
//	}
//	
//	@DeleteMapping(path="/deletePlanbyID/{plan_Id}")
//	public ResponseEntity<Plan> deletePlanbyID(@PathVariable int plan_Id) 
//	{
//		Plan plan= userServ.getPlanById(plan_Id);
//		adminServ.deleteplan(plan);
//		return ResponseEntity.status(HttpStatus.CREATED).body(plan);
//	}
//	
//	//this path will allow an admin or maybe a user to enable or disable a plan, not sure how to really add this though
//	@PostMapping(path="/toggle/{plan_Id}")
//	public ResponseEntity<Plan> toggle(@PathVariable int plan_Id) 
//	{
//		Plan plan= userServ.getPlanById(plan_Id);
//		//plan.set
//		return ResponseEntity.status(HttpStatus.CREATED).body(plan);
//	}
	
	
	/*
	 * @PostMapping (path = "/address/{id}") public ResponseEntity<Void>
	 * addAddress(@RequestBody Address newAddress){
	 * 
	 * if (newAddress !=null) { userServ.addNewAddress(newAddress); return
	 * ResponseEntity.status(HttpStatus.CREATED).build(); } return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); }
	 * 
	 * 
	 * 
	 * @GetMapping(path="/address/{id}") public ResponseEntity<Address>
	 * LookUpAddress(@RequestBody String token,
	 * 
	 * @PathVariable int user_id) { Address UserAddressId =
	 * userServ.getLookUpAddressByUser(user_id); if (UserAddressId!=null) { return
	 * ResponseEntity.ok(UserAddressId); } else { return
	 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @DeleteMapping(path = "/address/{id}") public ResponseEntity<String>
	 * deleteAddress(@RequestBody Address id) throws IncorrectAddressExeption {
	 * Address addressDeletedId = userServ.deleteAddressById(id); String token =
	 * Integer.toString(addressDeletedId.getAddress_id()); return
	 * ResponseEntity.ok(token);
	 * 
	 * }
	 */

//	
//	@GetMapping(path="/getActivePlans/{plan_Id}")
//	public ResponseEntity<Set<Plan>> getActivePlans() 
//	{
//		Set<Plan> plans= adminServ.getActivePlans();
//		return ResponseEntity.status(HttpStatus.CREATED).body(plans);
//	}
}
