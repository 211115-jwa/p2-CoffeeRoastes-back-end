package p2CoffeeRoastesvanquishbackend.services;

import p2CoffeeRoastesvanquishbackend.beans.Plan;

import java.util.Set;

import p2CoffeeRoastesvanquishbackend.beans.Address;
import p2CoffeeRoastesvanquishbackend.beans.CustomerPlan;

import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectAddressExeption;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;
import p2CoffeeRoastesvanquishbackend.exceptions.customerplandoesnotexist;

public interface UserService {
	public User register(User newUser) throws UsernameAlreadyExistsException;

	public User logIn(String username, String password) throws IncorrectCredentialsException;

	public Plan getPlan(String preference, String type, String quantity, String grind, String frequency);


	//public Address addNewAddress(Address newAddress);

	//public Address getLookUpAddressByUser(int user_id);

	CustomerPlan getcustomerPlanbyID(int customerplanID) throws customerplandoesnotexist;

	CustomerPlan deletecustomerPlanbyID(int customerplanID) throws customerplandoesnotexist;

	CustomerPlan CreateNewPlan(CustomerPlan cusutomerplan);

	CustomerPlan toggle(int customer_plan_id);

	Set<CustomerPlan> getallactiveplans(int user_id);

	//public Address deleteAddressById(Address id)throws IncorrectAddressExeption;

	

}
