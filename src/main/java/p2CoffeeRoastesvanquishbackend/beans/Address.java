package p2CoffeeRoastesvanquishbackend.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Address 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="address_id")
	private int address_id;
	@Column(name="zip")
	private int zip;
	@Column(name="user_id")
	private int user_id;
	@Column(name="street_address")
	private String street_address;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="apt")
	private String apt;


	public Address() {
		address_id = 1;
		zip = 1;
		user_id = 1;
		street_address= "Default Street Address";
		city= "Default city";
		state= "Default state";
		apt= "Default apt";
	}
	


	public int getAddress_id() {
		return address_id;
	}


	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

}
