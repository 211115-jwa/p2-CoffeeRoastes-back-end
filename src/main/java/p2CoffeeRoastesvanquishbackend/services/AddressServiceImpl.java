package p2CoffeeRoastesvanquishbackend.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.data.AddressRepository;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;

@Service

public class AddressServiceImpl implements AddressService {

	
	private AddressRepository addressRepo;
	
	
	@Autowired
	public AddressServiceImpl(AddressRepository AddressRepo) {
		
   	this.addressRepo= AddressRepo;

	}
	
	@Override
	public Address addNewAddress(Address newAddress) {
           
	    Address addressAdded =  addressRepo.save(newAddress);
		
	    if(addressAdded !=null) {
	    
		return addressAdded;}
		else {return null;}
	}

	
	


	@Override
	public Address deleteAddressById(int id) throws IncorrectAddressExeption {

		Address DeleteUserAddressFromDatabase =addressRepo.findById(id);
		if (DeleteUserAddressFromDatabase != null) {
			return DeleteUserAddressFromDatabase;

		} else {

			throw new IncorrectAddressExeption();
		}

	}

	@Override
	public Address getAddressById(int id) {
		Address address =addressRepo.findById(id);
		if (address != null) {
			return address;

		}

		return null;
	}

	@Override
	public Set<Address> searchAddressByUsername(String input) {


	Set<Address>  addressByUsername = addressRepo.findByUserUsername(input);
	return addressByUsername;

	}
}
