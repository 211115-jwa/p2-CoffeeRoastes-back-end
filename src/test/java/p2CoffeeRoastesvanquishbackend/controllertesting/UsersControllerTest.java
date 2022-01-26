package p2CoffeeRoastesvanquishbackend.controllertesting;
/*
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.petapp.PetApp2Application;
import com.revature.petapp.beans.Person;
import com.revature.petapp.exceptions.IncorrectCredentialsException;
import com.revature.petapp.exceptions.UsernameAlreadyExistsException;

import p2CoffeeRoastersvanquishbackend.servicestesting.UserService;

// this annotation tells spring boot to manage this test
// so that it can provide dependency injection, etc.
// you pass in your main class
@SpringBootTest(classes=PetApp2Application.class)
public class UsersControllerTest {
	// this annotation has Spring create a mock bean rather than the real bean.
	// it uses Mockito under the hood, so the purpose is the same.
	@MockBean
	private UserService userServ;
	// we'll have Spring inject the users controller so we can test it
	@Autowired
	private UsersController usersCtrl;
	// this object will allow us to mock HTTP requests sent to our application
	// so that we can finally unit test our controllers (rather than just integration test)
	private static MockMvc mockMvc;
	
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeAll
	public static void setUp() {
		// this initializes the Spring Web/MVC architecture for just one controller
		// so that we can isolate and unit test it
		mockMvc = MockMvcBuilders.standaloneSetup(UsersController.class).build();
	}
	
	@Test
	public void checkLoginUserExists() throws Exception {
		when(userServ.getUserById(1)).thenReturn(new Person());
		
		mockMvc.perform(get("/users/{userId}/auth", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andReturn();
	}
	
	@Test
	public void checkLoginUnauthorized() throws Exception {
		when(userServ.getUserById(0)).thenReturn(null);
		
		mockMvc.perform(get("/users/{userId}/auth", 0))
			.andExpect(status().isUnauthorized())
			.andReturn();
	}
	
	@Test
	public void getUserByIdUserExists() throws Exception {
		when(userServ.getUserById(1)).thenReturn(new Person());
		
		mockMvc.perform(get("/users/{userId}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andReturn();
	}
	
	@Test
	public void getUserByIdUserDoesNotExist() throws Exception {
		when(userServ.getUserById(0)).thenReturn(null);
		
		mockMvc.perform(get("/users/{userId}", 0))
			.andExpect(status().isNotFound())
			.andReturn();
	}
	
	@Test
	public void logInCorrectly() throws Exception {
		when(userServ.logIn("test", "test")).thenReturn(new Person());
		
		String jsonCredentials = "{"
				+ "\"username\":\"test\","
				+ "\"password\":\"test\""
				+ "}";
		mockMvc
			.perform(post("/users/auth").content(jsonCredentials).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string("0"))
			.andReturn();
	}
	
	@Test
	public void logInIncorrectCredentials() throws Exception {
		Map<String, String> credentials = new HashMap<>();
		credentials.put("username", "test");
		credentials.put("password", "test");
		
		when(userServ.logIn(credentials.get("username"), credentials.get("password")))
			.thenThrow(IncorrectCredentialsException.class);
		
		String jsonCredentials = objMapper.writeValueAsString(credentials);
		
		mockMvc
			.perform(post("/users/auth").content(jsonCredentials).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andReturn();
	}
	
	@Test
	public void registerSuccessfully() throws Exception {
		Person newUser = new Person();
		
		when(userServ.register(newUser)).thenReturn(newUser);
		Map<String,Integer> idMap = new HashMap<>();
		idMap.put("generatedId", 0);
		
		String jsonUser = objMapper.writeValueAsString(newUser);
		String jsonIdMap = objMapper.writeValueAsString(idMap);
		mockMvc.perform(post("/users").content(jsonUser).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(jsonIdMap))
				.andReturn();
	}
	
	@Test
	public void registerUsernameAlreadyExists() throws Exception {
		Person newUser = new Person();
		
		when(userServ.register(newUser)).thenThrow(UsernameAlreadyExistsException.class);
		
		String jsonUser = objMapper.writeValueAsString(newUser);
		mockMvc.perform(post("/users").content(jsonUser).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict())
				.andReturn();
	}
	
	@Test
	public void updateUserSuccessfully() throws Exception {
		Person userToEdit = new Person();
		userToEdit.setId(1);
		
		when(userServ.updateUser(userToEdit)).thenReturn(userToEdit);
		
		String jsonUser = objMapper.writeValueAsString(userToEdit);
		mockMvc.perform(put("/users/{userId}", 1).content(jsonUser).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonUser))
				.andReturn();
	}
	
	@Test
	public void updateUserIdDoesNotMatch() throws Exception {
		Person userToEdit = new Person();
		userToEdit.setId(1);
		
		String jsonUser = objMapper.writeValueAsString(userToEdit);
		mockMvc.perform(put("/users/{userId}", 5).content(jsonUser).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict())
				.andReturn();
	}
	
	@Test
	public void updateUserIsNull() throws Exception {
		Person userToEdit = null;
		
		String jsonUser = objMapper.writeValueAsString(userToEdit);
		mockMvc.perform(put("/users/{userId}", 5).content(jsonUser).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andReturn();
	}
	
	@Test
	public void updateUserDoesNotExistInDB() throws Exception {
		Person userToEdit = new Person();
		userToEdit.setId(1);
		
		when(userServ.updateUser(userToEdit)).thenReturn(null);
		
		String jsonUser = objMapper.writeValueAsString(userToEdit);
		mockMvc.perform(put("/users/{userId}", 1).content(jsonUser).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andReturn();
	}
}
*/