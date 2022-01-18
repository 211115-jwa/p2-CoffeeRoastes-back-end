package services;

import java.util.Set;

import org.apache.catalina.User;

import p2CoffeeRoastesvanquishbackend.beans.Plan;

public interface UserService 
{
	public User register(User newUser);
	public User logIn(String username, String password);
	public User getUserById(int id);
	public User updateUser(User userToUpdate);
	public User choosePlan(int planId, User user);
	public Set<Plan> viewPlans();
}
