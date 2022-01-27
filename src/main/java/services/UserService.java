package services;

import java.util.Set;


import Exceptions.IncorrectCredentialsException;
import Exceptions.UsernameAlreadyExistsException;
import p2CoffeeRoastesvanquishbackend.beans.Plan;
import p2CoffeeRoastesvanquishbackend.beans.User;

public interface UserService 
{
	public User register(User newUser) throws UsernameAlreadyExistsException;
	public User logIn(String username, String password) throws IncorrectCredentialsException;
	public User getUserById(int id);
	public User updateUser(User userToUpdate);
	public User choosePlan(int planId, User user);
	public Set<Plan> viewPlans();
	public Plan getPlanById(int id);

}
