package p2CoffeeRoastesvanquishbackend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p2CoffeeRoastesvanquishbackend.beans.CreditCard;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;
import p2CoffeeRoastesvanquishbackend.services.UserService;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "http://localhost:4200")

public class UsersController {

	private UserService userServ;

	@Autowired
	public UsersController(UserService userServ) {
		this.userServ = userServ;
	}

	public ResponseEntity<Map<String, Integer>> register(@RequestBody User newUser) {
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
	@PostMapping(path = "/auth")
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

	@PostMapping(path = "/card")
	public ResponseEntity<Void> addCreditCard(@RequestBody CreditCard newCreditCard) {

		if (newCreditCard != null) {
			userServ.addNewCreditCard(newCreditCard);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@GetMapping(path = "/{UserId}")
	public ResponseEntity<User> getCreditCardByUser(@PathVariable String creditCardUser) {

		User creditCard = userServ.getCreditCardByUser(creditCardUser);
		if (creditCard != null)
			return ResponseEntity.ok(creditCard);
		else
			return ResponseEntity.notFound().build();
	}

}
