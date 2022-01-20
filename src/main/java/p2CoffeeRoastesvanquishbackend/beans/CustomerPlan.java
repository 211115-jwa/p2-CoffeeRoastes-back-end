package p2CoffeeRoastesvanquishbackend.beans;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity

@Table( name= "customer_plan")

public class CustomerPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customer_plan_id =1;
	@ManyToOne
	@JoinColumn(name="plan_id")
	private Plan  plan;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@Column(name="plan_activated_date")
	private LocalDateTime planActivatedDate;
	private String active_plan;
	@ManyToOne
    @JoinColumn(name="address_id")
	private Address address;
	@ManyToOne
	@JoinColumn(name="card_id")
	private CreditCard credit_card;
	
	
	
	public CustomerPlan () {
		customer_plan_id  = 0;
		address = new Address();
		plan = new Plan();
		user = new User();
		planActivatedDate= LocalDateTime.now();	// ("dd-MM-yyyy HH:mm:ss");
		active_plan = "true";
		credit_card= new  CreditCard();
	}



	public int getCustomer_plan_id() {
		return customer_plan_id;
	}



	public void setCustomer_plan_id(int customer_plan_id) {
		this.customer_plan_id = customer_plan_id;
	}




	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public LocalDateTime getPlanActivatedDate() {
		return planActivatedDate;
	}

	public void setPlanActivatedDate(LocalDateTime planActivatedDate) {
		this.planActivatedDate = planActivatedDate;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public String getActive_plan() {
		return active_plan;
	}



	public void setActive_plan(String active_plan) {
		this.active_plan = active_plan;
	}



	public CreditCard getCard() {
		return credit_card;
	}



	public void setCard_id(CreditCard credit_card) {
		this.credit_card = credit_card;
	}

	
	public Plan getCustomerPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}



	@Override
	public String toString() {
		return "CustomerPlan [customer_plan_id=" + customer_plan_id + ", plan=" + plan + ", user=" + user
				+ ", planActivatedDate=" + planActivatedDate + ", active_plan=" + active_plan + ", address=" + address
				+ ", credit_card=" + credit_card + "]";
	}



	
	



}

	


