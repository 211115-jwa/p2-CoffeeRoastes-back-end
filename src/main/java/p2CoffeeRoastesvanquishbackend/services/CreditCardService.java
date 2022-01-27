package p2CoffeeRoastesvanquishbackend.services;

import java.util.Set;

import p2CoffeeRoastesvanquishbackend.beans.CreditCard;

public interface CreditCardService {

	public CreditCard addNewCreditCard(CreditCard newCreditCard);

	public Set <CreditCard> findCreditCardByUser(String username);

}
