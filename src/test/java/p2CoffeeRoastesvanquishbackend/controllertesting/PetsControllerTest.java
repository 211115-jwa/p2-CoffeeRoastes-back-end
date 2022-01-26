/*package p2CoffeeRoastesvanquishbackend.controllertesting;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

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
import com.revature.petapp.beans.Pet;
import com.revature.petapp.exceptions.AlreadyAdoptedException;

import p2CoffeeRoastersvanquishbackend.servicestesting.EmployeeService;
import p2CoffeeRoastersvanquishbackend.servicestesting.UserService;

@SpringBootTest(classes=PetApp2Application.class)
public class PetsControllerTest {
	@MockBean
	private UserService userServ;
	@MockBean
	private EmployeeService empServ;
	@Autowired
	private PetsController petsCtrl;
	
	// this object basically represents a mock of the Spring Web architecture
	private static MockMvc mockMvc;
	
	// this is a Jackson object mapper for JSON marshalling
	// (turning objects to JSON strings and vice versa
	private ObjectMapper objMapper = new ObjectMapper();
	
	@BeforeAll
	public static void setUp() {
		// sets up the minimum architecture to test our controller
		mockMvc = MockMvcBuilders.standaloneSetup(PetsController.class).build();
	}
	
	@Test
	public void getAvailablePets() throws Exception {
		when(userServ.viewAvailablePets()).thenReturn(Collections.emptySet());
		
		String jsonSet = objMapper.writeValueAsString(Collections.emptySet());
		
		mockMvc.perform(get("/pets"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(jsonSet))
			.andReturn();
	}
	
	@Test
	public void addPetSuccessfully() throws Exception {
		Pet newPet = new Pet();
		when(empServ.addNewPet(newPet)).thenReturn(1);
		
		String jsonPet = objMapper.writeValueAsString(newPet);
		
		mockMvc.perform(post("/pets").content(jsonPet).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andReturn();
	}
	
	@Test
	public void addPetWithoutPet() throws Exception {
		String jsonPet = objMapper.writeValueAsString(null);
		
		mockMvc.perform(post("/pets").content(jsonPet).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andReturn();
	}
	
	@Test
	public void adoptPetSuccessfully() throws Exception {
		Person newOwner = new Person();
		
		when(userServ.adoptPet(1, newOwner)).thenReturn(newOwner);
		
		String jsonOwner = objMapper.writeValueAsString(newOwner);
		
		mockMvc.perform(put("/pets/adopt/{petId}", 1).content(jsonOwner).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(jsonOwner))
			.andReturn();
	}
	
	@Test
	public void adoptPetAlreadyAdopted() throws Exception {
		Person newOwner = new Person();
		when(userServ.adoptPet(1, newOwner)).thenThrow(AlreadyAdoptedException.class);
		String jsonOwner = objMapper.writeValueAsString(newOwner);
		
		mockMvc.perform(put("/pets/adopt/{petId}", 1).content(jsonOwner).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isConflict())
		.andReturn();
	}
	
	@Test
	public void getPetByIdIsFound() throws Exception {
		when(empServ.getPetById(1)).thenReturn(new Pet());
		
		mockMvc.perform(get("/pets/{petId}", 1)).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void getPetByIdIsNotFound() throws Exception {
		when(empServ.getPetById(1)).thenReturn(null);
		
		mockMvc.perform(get("/pets/{petId}", 1)).andExpect(status().isNotFound()).andReturn();
	}
	
	@Test
	public void updatePetSuccessfully() throws Exception {
		Pet petToEdit = new Pet();
		petToEdit.setId(1);
		
		when(empServ.editPet(petToEdit)).thenReturn(petToEdit);
		String jsonPet = objMapper.writeValueAsString(petToEdit);
		
		mockMvc.perform(put("/pets/{petId}", 1).content(jsonPet).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(jsonPet))
			.andReturn();
	}
	
	@Test
	public void updatePetIdDoesNotMatch() throws Exception {
		Pet petToEdit = new Pet();
		petToEdit.setId(1);

		String jsonPet = objMapper.writeValueAsString(petToEdit);
		
		mockMvc.perform(put("/pets/{petId}", 5).content(jsonPet).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isConflict())
			.andReturn();
	}
	
	@Test
	public void updatePetIsNull() throws Exception {
		Pet petToEdit = null;
		
		String jsonPet = objMapper.writeValueAsString(petToEdit);
		
		mockMvc.perform(put("/pets/{petId}", 5).content(jsonPet).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andReturn();
	}
	
	@Test
	public void updatePetDoesNotExistInDB() throws Exception {
		Pet petToEdit = new Pet();
		petToEdit.setId(1);
		
		when(empServ.editPet(petToEdit)).thenReturn(null);
		String jsonPet = objMapper.writeValueAsString(petToEdit);
		
		mockMvc.perform(put("/pets/{petId}", 1).content(jsonPet).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andReturn();
	}
}
*/