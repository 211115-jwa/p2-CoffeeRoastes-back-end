package p2CoffeeRoastesvanquishbackend.beans;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name= "address")
public class Address 
{
	@Id
	@Column(name="address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="zip")
	private int zip;
	@ManyToOne
	@JoinColumn(name="user_id") 
	private User user;
	@Column(name="street_address")
	private String street_address;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="apt")
	private String apt;

//
//	public Address() {
//		id = 1;
//		zip = 1;
//		user = new User();
//		street_address= "Default Street Address";
//		city= "Default city";
//		state= "Default state";
//		apt= "Default apt";
//	}
//	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getStreet_address() {
		return street_address;
	}


	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getApt() {
		return apt;
	}


	public void setApt(String apt) {
		this.apt = apt;
	}



	@Override
	public String toString() {
		return "Address [address_id=" + id + ", zip=" + zip + ", user=" + user + ", street_address="
				+ street_address + ", city=" + city + ", state=" + state + ", apt=" + apt + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(id, apt, city, state, street_address, user, zip);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return id == other.id && Objects.equals(apt, other.apt) && Objects.equals(city, other.city)
				&& Objects.equals(state, other.state) && Objects.equals(street_address, other.street_address)
				&& Objects.equals(user, other.user) && zip == other.zip;
	}

}
