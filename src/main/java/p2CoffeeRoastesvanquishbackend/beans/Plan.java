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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int plan_Id;
	@Column
	private String coffee_preference;
	@Column
	private String coffee_type;
	@Column
	private String coffee_quantity;
	@Column
	private String coffee_grind;
	@Column
	private String delivery_frenquency ;
	@Column
	private double price;

	public Plan() {

		plan_Id = 1;
		coffee_type = " ";
		coffee_quantity = " ";
		coffee_grind = "";
		delivery_frenquency = "";
		price = 12.22;

	}

	public int getplan_Id() {
		return plan_Id;
	}

	public void setplan_Id(int plan_Id) {
		this.plan_Id = plan_Id;
	}

	public String getCoffee_preference() {
		return coffee_preference;
	}

	public void setCoffee_preference(String coffee_preference) {
		this.coffee_preference = coffee_preference;
	}

	public String getCoffee_Type() {
		return coffee_type;
	}

	public void setCoffee_Type(String coffee_type) {
		this.coffee_type = coffee_type;
	}

	public String getCoffee_quantity() {
		return coffee_quantity;
	}

	public void setCoffee_quantity(String coffee_quantity) {
		this.coffee_quantity = coffee_quantity;
	}

	public String getCoffee_grind() {
		return coffee_grind;
	}

	public void setCoffee_grind(String coffee_grind) {
		this.coffee_grind = coffee_grind;
	}

	public String getDelivery_frenquency() {
		return delivery_frenquency;
	}

	public void setDelivery_frenquency(String delivery_frenquency) {
		this.delivery_frenquency = delivery_frenquency;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Plan [plan_Id=" + plan_Id + ", coffee_preference=" + coffee_preference + ", coffee_Type=" + coffee_type
				+ ", coffee_quantity=" + coffee_quantity + ", coffee_grind=" + coffee_grind + ", delivery_frenquency="
				+ delivery_frenquency + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(coffee_grind, coffee_preference, coffee_quantity, coffee_type, delivery_frenquency, plan_Id,
				price);
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
		return Objects.equals(coffee_grind, other.coffee_grind)
				&& Objects.equals(coffee_preference, other.coffee_preference)
				&& Objects.equals(coffee_quantity, other.coffee_quantity)
				&& Objects.equals(coffee_type, other.coffee_type)
				&& Objects.equals(delivery_frenquency, other.delivery_frenquency) && plan_Id == other.plan_Id
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}

}
