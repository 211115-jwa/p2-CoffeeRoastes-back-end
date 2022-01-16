package p2CoffeeRoastesvanquishbackend.beans;
import java.time.LocalDateTime;
import java.time.LocalTime;  


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;


	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private LocalDateTime createdOn;
	@ManyToOne
	@JoinColumn(name="role_id") 

	private Role role;
	
	public User() {
		id = 0;
		firstName = "First";
		lastName = "Last";
		username = "username";
		email = "johncena@yahoo.com";
		createdOn = LocalDateTime.now();	// ("dd-MM-yyyy HH:mm:ss");
		role = new Role();

	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
