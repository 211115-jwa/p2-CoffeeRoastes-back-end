package p2CoffeeRoastesvanquishbackend.beans;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "plan")
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int plan_id;
	@Column(name = "coffee_preference")
	private String preference;
	@Column(name = "coffee_type")
	private String type;
	@Column(name = "coffee_quantity")
	private String quantity;
	@Column(name = "coffee_grind")
	private String grind;
	@Column(name = "delivery_frequency")
	private String frequency ;
	@Column(name = "price")
	private double price;

	public Plan() {

		plan_id = 1;
		preference = "";
		type = "";
		quantity = " ";
		grind = "";
		frequency = "";
		price = 12.22;

	}

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getGrind() {
		return grind;
	}

	public void setGrind(String grind) {
		this.grind = grind;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Plan [plan_id=" + plan_id + ", preference=" + preference + ", type=" + type + ", quantity=" + quantity
				+ ", grind=" + grind + ", frenquency=" + frequency + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(frequency, grind, plan_id, preference, price, quantity, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plan other = (Plan) obj;
		return Objects.equals(frequency, other.frequency) && Objects.equals(grind, other.grind)
				&& plan_id == other.plan_id && Objects.equals(preference, other.preference)
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(type, other.type);
	}





}
