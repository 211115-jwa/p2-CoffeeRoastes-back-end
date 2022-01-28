package p2CoffeeRoastesvanquishbackend.controllers;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
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

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;
import p2CoffeeRoastesvanquishbackend.services.AddressService;

@Configuration
@RestController
@RequestMapping(path = "/address")
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {

	private AddressService addressService;

	public AddressController() {

		super();

	}

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Void> addAddress(@RequestBody Address newAddress) {

		if (newAddress != null) {

			addressService.addNewAddress(newAddress);
			return ResponseEntity.status(HttpStatus.CREATED).build();
	}else {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
		
		

	}
	
	
	
		@PostMapping(path="/user")
		public ResponseEntity<Set<Address>> getAddressByUsername(@RequestBody Map<String, String>  input) {
			String username = input.get("username");
			Set<Address> UserAddressName = addressService.searchAddressByUsername(username);
			if (UserAddressName!=null) {
				return ResponseEntity.ok(UserAddressName);
			} else {
				
				   Set<Address> EmptySet = Collections.<Address>emptySet();
					return ResponseEntity.ok(EmptySet);
			}
			
			
		}

	

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteAddress(@RequestBody int id) throws IncorrectAddressExeption {
		Address addressDeletedId = addressService.deleteAddressById(id);
		String token = Integer.toString(addressDeletedId.getId());
		return ResponseEntity.ok(token);

	}

	@GetMapping(path = "/{Id}")
	public ResponseEntity<Address> LocateUserAddressById(@PathVariable int id) {
		Address addressById = addressService.getAddressById(id);
		if (addressById != null)
			return ResponseEntity.ok(addressById);
		else
			return ResponseEntity.notFound().build();
	}

}
