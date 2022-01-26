package p2CoffeeRoastesvanquishbackend.services;

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
	
	// This function is to Add Address where user is

	@Override
	public Address addNewAddress(Address newAddress) {
           
	    Address addressAdded =  addressRepo.save(newAddress);
		
	    if(addressAdded !=null) {
	    
		return addressAdded;	}
		else {return null;}
	}

	
	
	// This function is to Look up address by User id

	@Override
	public Address getLookUpAddressByUser(int user_id) {
		Address UserByAddress = addressRepo.findByUserId(user_id);
		if (UserByAddress != null) {
			addressRepo.findById(user_id);
		}
		return UserByAddress;
	}

    //  This function is to Delete address by Id

	@Override
	public Address deleteAddressById(int id) throws IncorrectAddressExeption {

		Address DeleteUserAddressFromDatabase =addressRepo.findByUserId(id);
		if (DeleteUserAddressFromDatabase != null) {
			return DeleteUserAddressFromDatabase;

		} else {

			throw new IncorrectAddressExeption();
		}

	}
}
