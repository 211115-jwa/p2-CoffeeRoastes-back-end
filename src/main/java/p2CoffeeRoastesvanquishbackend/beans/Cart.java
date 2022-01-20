package p2CoffeeRoastesvanquishbackend.beans;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table( name= "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cart_id;
	@ManyToOne
	@JoinColumn(name="plan_id")
	private Plan plan;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Cart() {
		cart_id= 1;
		plan = new Plan();
		user = new User();	
	}


	public int getCart_id() {
		return cart_id;
	}


	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}


	public Plan getPlan() {
		return plan;
	}


	public void setPlan(Plan plan) {
		this.plan = plan;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", plan=" + plan + ", user=" + user + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(cart_id, plan, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return cart_id == other.cart_id && Objects.equals(plan, other.plan) && Objects.equals(user, other.user);
	}


}
