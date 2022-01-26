package p2CoffeeRoastesvanquishbackend.beans;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table( name= "credit_card")
public class CreditCard 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="card_id")
	private int id;
	@Column(name="card_number")
	private String card_number;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public CreditCard() {
		id = 1;
		card_number = "0000000000000000";
		user = new User();
	}

	public int getCard_id() {
		return id;
	}

	public void setCard_id(int card_id) {
		this.id = card_id;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, card_number, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		return id == other.id && Objects.equals(card_number, other.card_number)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "CreditCard [card_id=" + id + ", card_number=" + card_number + ", user=" + user + "]";
	}

}
