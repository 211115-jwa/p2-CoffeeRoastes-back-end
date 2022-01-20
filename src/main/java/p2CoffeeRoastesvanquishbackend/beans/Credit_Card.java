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

public class Credit_Card 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="card_id")
	private int card_id;
	@Column(name="card_number")
	private int card_number;
	@Column(name="user_id")
	private int user_id;

	public Credit_Card() {
		card_id = 1;
		card_number = 1;
		user_id = 1;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public int getCard_number() {
		return card_number;
	}

	public void setCard_number(int card_number) {
		this.card_number = card_number;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
