package p2CoffeeRoastesvanquishbackend.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import p2CoffeeRoastesvanquishbackend.beans.CreditCard;

import p2CoffeeRoastesvanquishbackend.annotations.Authenticate;
//import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.beans.CustomerPlan;
import p2CoffeeRoastesvanquishbackend.beans.Plan;

import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.exceptions.CustomerDoesNotExistException;
//import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;

import p2CoffeeRoastesvanquishbackend.exceptions.customerplandoesnotexist;
import p2CoffeeRoastesvanquishbackend.services.AdminService;
import p2CoffeeRoastesvanquishbackend.services.UserService;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "http://localhost:4200")

public class UsersController {

	private UserService userServ;
	private AdminService adminServ;

	@Autowired
	public UsersController(UserService userServ) {
		this.userServ = userServ;
	}

	private static Logger log = LogManager.getLogger(UsersController.class);

	
	public ResponseEntity<Map<String,Integer>> register(@RequestBody User newUser) {

		try {
			log.info("Registering New User: "+newUser.getUsername());
			newUser = userServ.register(newUser);
			Map<String, Integer> newIdMap = new HashMap<>();
			newIdMap.put("generatedId", newUser.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(newIdMap);
		} catch (UsernameAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	// POST to /users/auth
	@PostMapping(path = "/auth")
	public ResponseEntity<String> logIn(@RequestBody Map<String, String> credentials) {
		
		String username = credentials.get("username");
		String password = credentials.get("password");

		log.info("Attmpt to login: "+username+" "+password);

		try {
			User person = userServ.logIn(username, password);
			String token = Integer.toString(person.getId());
			log.info("Logged In: "+username+" "+password);
			return ResponseEntity.ok(token);
		} catch (IncorrectCredentialsException e) {
			log.error("Failure to log In: "+username+" "+password);
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(path = "/getplan")
	public ResponseEntity<Plan> getPlan(@RequestBody Map<String, String> input) {
		String preference = input.get("preference");
		String type = input.get("type");
		String quantity = input.get("quantity");
		String grind = input.get("grind");

		String frequency= input.get("frequency");
		
		log.info("Attempt to Get Plan:"+preference+" "+type+" "+quantity+" "+grind+" "+frequency);


		try {
			Plan plan = userServ.getPlan(preference, type, quantity, grind, frequency);
//			String token = Integer.toString(person.getId());
			log.info("Sucsessfully got Plan:"+preference+" "+type+" "+quantity+" "+grind+" "+frequency);
			return ResponseEntity.ok(plan);
		} finally {

		}
//		catch (IncorrectCredentialsException e) {
//			return ResponseEntity.notFound().build();
//		}
	}

	// GET to /users/{userId}/auth

	@GetMapping(path="/{userId}/auth")
	public ResponseEntity<User> checkLogin(@PathVariable int userId) throws CustomerDoesNotExistException {
		User loggedInPerson = userServ.getUserById(userId);
		if (loggedInPerson!=null) {
			log.info("Sucsessfully Authenticated:"+loggedInPerson.getUsername());

			return ResponseEntity.ok(loggedInPerson);
		} else {
			log.error("Failure to authenticate: "+ loggedInPerson.getUsername());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	// GET to /users/{userId}

	@GetMapping(path="/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) throws CustomerDoesNotExistException {

		User user = userServ.getUserById(userId);
		if (user != null)
		{
			log.info("Got "+user.getUsername() +"by id: "+ userId);
			return ResponseEntity.ok(user);
		}
		else
		{
			log.error("Failed to get by id: "+userId);
			return ResponseEntity.notFound().build();
		}
	}

	// PUT to /users/{userId}
	@Authenticate(requiredRoles = {})
	@PutMapping(path = "/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User userToEdit, @PathVariable int userId) {
		if (userToEdit != null && userToEdit.getId() == userId) {
			userToEdit = userServ.updateUser(userToEdit);
			if (userToEdit != null)
				return ResponseEntity.ok(userToEdit);
			else
				return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}







	@PostMapping(path = "/create/userplan")
	public ResponseEntity<Void>createCustomerPlan(@RequestBody CustomerPlan newPlan) {
	System.out.println(newPlan);
		if (newPlan != null) {
			userServ.CreateNewPlan(newPlan);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	// Get to /users/getCustomerPlans
	@GetMapping(path = "/getCustomerPlans/{user_Id}")
	public ResponseEntity<Set<Plan>> getCustomerPlan(@PathVariable int user_Id) {
		Set<Plan> plans = adminServ.getPlansByUserId(user_Id);
		return ResponseEntity.status(HttpStatus.CREATED).body(plans);
	}

//	// Get to /users/getcustomerPlanbyID
//	@GetMapping(path = "/getcustomerPlanbyID/{customer_plan_id}")
//	public ResponseEntity<CustomerPlan> getPlanbyID(@PathVariable int customer_plan_id)
//			throws customerplandoesnotexist {
//		CustomerPlan customerplan = userServ.getcustomerPlanbyID(customer_plan_id);
//		return ResponseEntity.status(HttpStatus.CREATED).body(customerplan);
//	}



//	// Put to /users/togglecustomerplan
//	@PutMapping(path = "/togglecustomerplan/{customer_plan_id}")
//	public ResponseEntity<CustomerPlan> togglecustomerplan(@PathVariable int customer_plan_id)
//			throws customerplandoesnotexist {
//		CustomerPlan customerplan = userServ.toggle(customer_plan_id);
//		return ResponseEntity.status(HttpStatus.CREATED).body(customerplan);
//	}

//	// Get to /users/getallactiveplans
//	@GetMapping(path = "/getallactiveplans/{user_id}")
//	public ResponseEntity<Set<CustomerPlan>> getallactiveplans(@PathVariable int user_id) {
//		Set<CustomerPlan> activecustomerplans = userServ.getallactiveplans(user_id);
//		return ResponseEntity.status(HttpStatus.CREATED).body(activecustomerplans);
//	}

	
	
	// Post to /createuser
	@PostMapping(path="/createuser")
	public ResponseEntity<User> createuser(@RequestBody User newuser) throws UsernameAlreadyExistsException 
	{
		User customer= userServ.register(newuser);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	
	@PostMapping(path = "/allmyplans")
	public ResponseEntity<Set<CustomerPlan>>findPlansByUsername(@RequestBody Map<String, String>  input) {
		String username = input.get("username");
		Set<CustomerPlan> cards =  userServ.getCustomerPlansByName(username);

		if (cards!=null) {
			return ResponseEntity.ok(cards);
		} else {
	
			   Set<CustomerPlan> EmptySet = Collections.<CustomerPlan>emptySet();
				return ResponseEntity.ok(EmptySet);
		}
	}

	

}
