package p2CoffeeRoastesvanquishbackend.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "plan", schema = "coffee")
public class Plan {
	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)

	private int plan_Id = 1;

	private String coffee_preference = " ";

	private String coffee_Type = "";

	private String coffee_quantity = "Medio";

	private String coffee_grind = "";

	private String delivery_frenquency = "";

	private int price = 1;

	public Plan() {
		super();

		plan_Id = 1;
		coffee_Type = " ";
		coffee_quantity = " ";
		coffee_grind = "";
		delivery_frenquency = "";
		price = 12;

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
		return coffee_Type;
	}

	public void setCoffee_Type(String coffee_Type) {
		this.coffee_Type = coffee_Type;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Plan [plan_Id=" + plan_Id + ", coffee_preference=" + coffee_preference + ", coffee_Type=" + coffee_Type
				+ ", coffee_quantity=" + coffee_quantity + ", coffee_grind=" + coffee_grind + ", delivery_frenquency="
				+ delivery_frenquency + ", price=" + price + "]";
	}

}
