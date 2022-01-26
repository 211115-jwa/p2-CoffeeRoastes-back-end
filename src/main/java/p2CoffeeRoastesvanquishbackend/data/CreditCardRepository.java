package p2CoffeeRoastesvanquishbackend.data;

import org.springframework.data.jpa.repository.JpaRepository;

import p2CoffeeRoastesvanquishbackend.beans.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer>{
	public CreditCard addNewCreditCard(CreditCard newCreditCard);
	public CreditCard getCreditCardByUser(String creditCardUser);

}

