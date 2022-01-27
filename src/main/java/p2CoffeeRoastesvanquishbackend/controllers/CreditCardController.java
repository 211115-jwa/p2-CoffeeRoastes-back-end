package p2CoffeeRoastesvanquishbackend.controllers;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.beans.CreditCard;
import p2CoffeeRoastesvanquishbackend.services.CreditCardService;

@Configuration
@RestController
@RequestMapping(path = "/card")
@CrossOrigin(origins = "http://localhost:4200")
public class CreditCardController {

	private CreditCardService creditCardService;

	public CreditCardController() {
		super();
	}

	@Autowired
	public CreditCardController(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Void> addCreditCard(@RequestBody CreditCard newCreditCard) {
		if (newCreditCard != null) {
			creditCardService.addNewCreditCard(newCreditCard);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping(path = "/user")
	public ResponseEntity<Set<CreditCard>>findCardsByUsername(@RequestBody Map<String, String>  input) {
		String username = input.get("username");
		Set<CreditCard> cards = creditCardService.findCreditCardByUser(username);

		if (cards!=null) {
			return ResponseEntity.ok(cards);
		} else {
	
			   Set<CreditCard> EmptySet = Collections.<CreditCard>emptySet();
				return ResponseEntity.ok(EmptySet);
		}
	}

}