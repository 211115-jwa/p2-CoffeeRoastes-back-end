package p2CoffeeRoastesvanquishbackend.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
	//private Plan  plan;
	 private List<CustomerPlan> customerPlan;
	
	
	/*
	 * @ManyToMany
	 * 
	 * @JoinColumn(name="user_id") private User users;
	 */
	
	@ManyToMany(mappedBy = "customer_plan")
	private User users;
	
	
	private LocalDateTime planActivatedDate;
	private String active_plan = "";
	
    @OneToOne
    @JoinColumn(name="address_id")
	private Address address;
    
    @OneToOne
	@JoinColumn(name="card_id")
	private Credit_Card  credit_card;
	
	
	
	public CustomerPlan () {
		customer_plan_id  = 0;
		//plan = new Plan() ;
		customerPlan = new ArrayList<CustomerPlan>();
		users = new User();
		planActivatedDate= LocalDateTime.now();	// ("dd-MM-yyyy HH:mm:ss");
		active_plan = "";
		 credit_card= new  Credit_Card();
		
		

	}



	public int getCustomer_plan_id() {
		return customer_plan_id;
	}



	public void setCustomer_plan_id(int customer_plan_id) {
		this.customer_plan_id = customer_plan_id;
	}



	/*
	 * public Plan getPlan() { return plan; }
	 * 
	 * 
	 * 
	 * public void setPlan(Plan plan) { this.plan = plan; }
	 */



	public User getUser() {
		return users;
	}



	public void setUser(User user) {
		this.users = user;
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



	public void setAddress_id(Address address) {
		this.address = address;
	}



	public String getActive_plan() {
		return active_plan;
	}



	public void setActive_plan(String active_plan) {
		this.active_plan = active_plan;
	}



	public  Credit_Card getCard_id() {
		return credit_card;
	}



	public void setCard_id( Credit_Card credit_card) {
		this.credit_card = credit_card;
	}

	/**
	 * @return the customerPlan
	 */
	public List<CustomerPlan> getCustomerPlan() {
		return customerPlan;
	}



	/**
	 * @param customerPlan the customerPlan to set
	 */
	public void setCustomerPlan(List<CustomerPlan> customerPlan) {
		this.customerPlan = customerPlan;
	}



	@Override
	public String toString() {
		return "CustomerPlan [customer_plan_id=" + customer_plan_id + ", customerPlan=" + customerPlan + ", users="
				+ users + ", planActivatedDate=" + planActivatedDate + ", active_plan=" + active_plan + ", address="
				+ address + ", card_id=" + credit_card + "]";
	}
	



}

	


