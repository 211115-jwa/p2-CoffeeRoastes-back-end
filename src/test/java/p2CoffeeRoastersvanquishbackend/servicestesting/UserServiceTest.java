package p2CoffeeRoastersvanquishbackend.servicestesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import p2CoffeeRoastesvanquishbackend.P2CoffeeRoastesVanquishBackEndApplication;
import p2CoffeeRoastesvanquishbackend.beans.CustomerPlan;
import p2CoffeeRoastesvanquishbackend.beans.User;
import p2CoffeeRoastesvanquishbackend.data.AddressRepository;
import p2CoffeeRoastesvanquishbackend.data.CartRepository;
import p2CoffeeRoastesvanquishbackend.data.CreditCardRepository;
import p2CoffeeRoastesvanquishbackend.data.CustomerPlanRepository;
import p2CoffeeRoastesvanquishbackend.data.PlanRepository;
import p2CoffeeRoastesvanquishbackend.data.UserRepository;
import p2CoffeeRoastesvanquishbackend.exceptions.CustomerDoesNotExistException;
import p2CoffeeRoastesvanquishbackend.exceptions.IncorrectCredentialsException;
import p2CoffeeRoastesvanquishbackend.exceptions.UsernameAlreadyExistsException;
import p2CoffeeRoastesvanquishbackend.exceptions.customerplandoesnotexist;
import p2CoffeeRoastesvanquishbackend.services.UserService;


@SpringBootTest(classes=P2CoffeeRoastesVanquishBackEndApplication.class)
public class UserServiceTest {
	@MockBean
	private PlanRepository planRepo;
	@MockBean
	private UserRepository personRepo;
	@MockBean
	private CustomerPlanRepository customerplanRepo;
	@MockBean
	private CartRepository cartRepo;
	@MockBean
	private AddressRepository addressRepo;
	@MockBean
	private CreditCardRepository creditcardRepo;
	
	@Autowired
	private UserService userServ;

	private static Set<CustomerPlan> mockcurrentplans;
	private static Set<User> mockcurrenUsers;
	
	@BeforeAll
	public static void mockAvailablePetsSetup() {
		mockcurrentplans = new HashSet<>();
		
		for (int i=1; i<=5; i++) 
		{
			CustomerPlan custplan = new CustomerPlan();
			User person = new User();
			person.setId(99999);
			custplan.setCustomer_plan_id(i);
			custplan.setUser(person);
			mockcurrentplans.add(custplan);
		}
	}
	
	@Test
	public void logInSuccessfully() throws IncorrectCredentialsException {
		// input setup
		String username="qwertyuiop";
		String password="pass";
		
		// set up the mocking
		User mockPerson = new User();
		mockPerson.setUsername(username);
		mockPerson.setPassword(password);
		when(personRepo.findByUsername(username)).thenReturn(mockPerson);
		
		// call the method we're testing
		User actualPerson = userServ.logIn(username, password);
		
		// assert the expected behavior/output
		assertEquals(mockPerson,actualPerson);
	}
	
	@Test
	public void logInIncorrectPassword() {
		String username="qwertyuiop";
		String password="12345";
		
		User mockPerson = new User();
		mockPerson.setUsername(username);
		mockPerson.setPassword("pass");
		when(personRepo.findByUsername(username)).thenReturn(mockPerson);
		
		assertThrows(IncorrectCredentialsException.class, () -> {
			userServ.logIn(username, password);
		});
	}
	
	@Test
	public void logInUsernameDoesNotExist() {
		String username="asdfghjkl";
		String password="pass";
		
		when(personRepo.findByUsername(username)).thenReturn(null);
		
		assertThrows(IncorrectCredentialsException.class, () -> {
			userServ.logIn(username, password);
		});
	}
	
	@Test
	public void registerPersonSuccessfully() throws UsernameAlreadyExistsException {
		User person = new User();
		person.setId(10);
		
		when(personRepo.save(person)).thenReturn(person);
		
		User actualPerson = userServ.register(person);
		assertEquals(10, actualPerson.getId());
	}
	
	@Test
	public void registerPersonSomethingWrong() throws UsernameAlreadyExistsException {
		User person = new User();
		when(personRepo.save(person)).thenThrow(new RuntimeException());
		User actualPerson = userServ.register(person);
		assertNull(actualPerson);
	}
	
	@Test
	public void registerPersonUsernameAlreadyExists() {
		User person = new User();
		when(personRepo.save(person)).thenThrow(new RuntimeException("unique constraint violation"));

		assertThrows(UsernameAlreadyExistsException.class, () -> {
			userServ.register(person);
		});
	}
	
	@Test
	public void findActivePlansPOSITIVE() 
	{
		User user = new User();
		user.setId(99999);
		when(customerplanRepo.findByActiveAndUser("True", user)).thenReturn(mockcurrentplans);
		Set<CustomerPlan> activePlans = customerplanRepo.findByActiveAndUser("True", user);
		boolean onlyactive = true;
		for (CustomerPlan customerplan : activePlans) {
			if (!customerplan.getActive().equals("True"))
				onlyactive = false;
		}
		
		assertTrue(onlyactive);
	}
	
	@Test
	public void findActivePlansNEGATIVE() 
	{
		User user = new User();
		user.setId(99999);
		when(customerplanRepo.findByActiveAndUser("asdasd", user)).thenReturn(mockcurrentplans);
		Set<CustomerPlan> activePlans = customerplanRepo.findByActiveAndUser("asdasd", user);
		boolean onlyactive = true;
		for (CustomerPlan customerplan : activePlans) {
			if (!customerplan.getActive().equals("asdasd"))
				onlyactive = false;
		}
		
		assertTrue(onlyactive);
	}
	
	@Test
	public void CreateNewPlanPositive() 
	{
		CustomerPlan newplan = new CustomerPlan();
		newplan.setCustomer_plan_id(77777);
		
		assertNotNull(newplan.getCustomer_plan_id());
	}
	
	@Test
	public void CreateNewPlanNegative() 
	{
		CustomerPlan newplan = new CustomerPlan();
		
		assertNotNull(newplan.getCustomer_plan_id());
	}
	
	@Test
	public void GetPlanPositive() throws customerplandoesnotexist 
	{
		CustomerPlan newplan = new CustomerPlan();
		newplan.setCustomer_plan_id(77777);
		mockcurrentplans.add(newplan);
		
		assertEquals(newplan, userServ.getcustomerPlanbyID(77777));
	}
	
	@Test
	public void GetPlanNegative() throws customerplandoesnotexist 
	{
		CustomerPlan newplan = new CustomerPlan();
		newplan.setCustomer_plan_id(77777);
		mockcurrentplans.add(newplan);
		
		assertEquals(newplan, userServ.getcustomerPlanbyID(999945));
	}
	
	@Test
	public void TogglePositive() throws customerplandoesnotexist 
	{
		CustomerPlan newplan = new CustomerPlan();
		newplan.setCustomer_plan_id(77777);
		newplan.setActive("True");
		mockcurrentplans.add(newplan);
		userServ.toggle(77777);
		assertEquals("False", userServ.getcustomerPlanbyID(7777).getActive());
	}
	
	@Test
	public void ToggleNegative() throws customerplandoesnotexist 
	{
		CustomerPlan newplan = new CustomerPlan();
		newplan.setCustomer_plan_id(77777);
		newplan.setActive("True");
		mockcurrentplans.add(newplan);
		
		assertEquals("PIE", userServ.getcustomerPlanbyID(999945));
	}
	
	@Test
	public void updateUserPositive() throws CustomerDoesNotExistException
	{
		User newuser = new User();
		newuser.setUsername("Kahewm");
		newuser.setId(123587);
		mockcurrenUsers.add(newuser);
		
		newuser.setUsername("Katthewm");
		userServ.updateUser(newuser);
		assertEquals("Katthewm", userServ.getUserById(123587).getUsername());
	}
	
	@Test
	public void updateUserNegative() throws CustomerDoesNotExistException 
	{
		User newuser = new User();
		newuser.setUsername("Kahewm");
		newuser.setId(123587);
		mockcurrenUsers.add(newuser);
		
		newuser.setUsername("Katthewm");
		userServ.updateUser(newuser);
		assertEquals("KABOOM", userServ.getUserById(123587).getUsername());
	}
}
