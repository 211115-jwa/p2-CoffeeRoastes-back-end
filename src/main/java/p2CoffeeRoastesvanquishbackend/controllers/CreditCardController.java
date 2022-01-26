package p2CoffeeRoastesvanquishbackend.controllers;

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

import p2CoffeeRoastesvanquishbackend.beans.CreditCard;
import p2CoffeeRoastesvanquishbackend.services.CreditCardService;

@Configuration
@RestController
@RequestMapping(path = "/creditcard")
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

	@GetMapping(path = "/creditcard/{id}")
	public ResponseEntity<CreditCard> LookUpCreditCard(@RequestBody String token, @PathVariable int user_id) {

		CreditCard UserCreditCardId = (CreditCard) creditCardService.getLookUpCreditCardByUser(user_id);
		if (UserCreditCardId != null) {
			return ResponseEntity.ok(UserCreditCardId);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}