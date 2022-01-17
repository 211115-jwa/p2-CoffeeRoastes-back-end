package p2CoffeeRoastesvanquishbackend.beans;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity

@Table( name= "customer_plan", schema = "coffee")

public class CustomerPlan {
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int customer_plan_id =1;
	
	@OneToMany
	@JoinColumn(name="plan_id")
	private int plan_id =1;
	
	
	@ManyToMany
	@JoinColumn(name="user_id")
	private int user_id =1;
	
	
	private LocalDateTime planActivatedDate;
	private String active_plan = "";
	
    @OneToOne
    @JoinColumn(name="address_id")
	private int address_id = 1;
    
    @OneToOne
	@JoinColumn(name="card_id")
	private int card_id = 23;
	
	
	
	public CustomerPlan () {
		customer_plan_id  = 0;
		plan_id = 0;
		user_id =1;
		planActivatedDate= LocalDateTime.now();	// ("dd-MM-yyyy HH:mm:ss");
		active_plan = "";
		card_id = 23;
		
		

	}



	public int getCustomer_plan_id() {
		return customer_plan_id;
	}



	public void setCustomer_plan_id(int customer_plan_id) {
		this.customer_plan_id = customer_plan_id;
	}



	public int getPlan_id() {
		return plan_id;
	}



	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}



	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public LocalDateTime getPlanActivatedDate() {
		return planActivatedDate;
	}



	public void setPlanActivatedDate(LocalDateTime planActivatedDate) {
		this.planActivatedDate = planActivatedDate;
	}



	public int getAddress_id() {
		return address_id;
	}



	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}



	public String getActive_plan() {
		return active_plan;
	}



	public void setActive_plan(String active_plan) {
		this.active_plan = active_plan;
	}



	public int getCard_id() {
		return card_id;
	}



	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}



	@Override
	public String toString() {
		return "CustomerPlan [customer_plan_id=" + customer_plan_id + ", plan_id=" + plan_id + ", user_id=" + user_id
				+ ", planActivatedDate=" + planActivatedDate + ", active_plan=" + active_plan + ", address_id="
				+ address_id + ", card_id=" + card_id + "]";
	}
	
	

}
