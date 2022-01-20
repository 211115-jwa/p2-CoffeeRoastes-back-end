package p2CoffeeRoastesvanquishbackend.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;
import services.AdminService;
import services.UserService;

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
	
	
	// POST to /users/createPlan
	@PostMapping(path="/createPlan")
	public ResponseEntity<Plan> logIn(@RequestBody Plan newPlan) 
	{
		adminServ.addNewPlan(newPlan);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPlan);
	}
	
	// Get to /users/createPlan
	@GetMapping(path="/getCustomerPlan/{user_Id}")
	public ResponseEntity<Set<Plan>> getCustomerPlan(@PathVariable int user_Id) 
	{
		Set<Plan> plans= adminServ.getPlansByUserId(user_Id);
		return ResponseEntity.status(HttpStatus.CREATED).body(plans);
	}

	@GetMapping(path="/getPlanbyID/{plan_Id}")
	public ResponseEntity<Plan> getPlanbyID(@PathVariable int plan_Id) 
	{
		Plan plan= userServ.getPlanById(plan_Id);
		return ResponseEntity.status(HttpStatus.CREATED).body(plan);
	}
	
	@DeleteMapping(path="/deletePlanbyID/{plan_Id}")
	public ResponseEntity<Plan> deletePlanbyID(@PathVariable int plan_Id) 
	{
		Plan plan= userServ.getPlanById(plan_Id);
		adminServ.deleteplan(plan);
		return ResponseEntity.status(HttpStatus.CREATED).body(plan);
	}
	
	//this path will allow an admin or maybe a user to enable or disable a plan, not sure how to really add this though
	@PostMapping(path="/toggle/{plan_Id}")
	public ResponseEntity<Plan> toggle(@PathVariable int plan_Id) 
	{
		Plan plan= userServ.getPlanById(plan_Id);
		//plan.set
		return ResponseEntity.status(HttpStatus.CREATED).body(plan);
	}
	
	@GetMapping(path="/getActivePlans/{plan_Id}")
	public ResponseEntity<Set<Plan>> getActivePlans() 
	{
		Set<Plan> plans= adminServ.getActivePlans();
		return ResponseEntity.status(HttpStatus.CREATED).body(plans);
	}
}
