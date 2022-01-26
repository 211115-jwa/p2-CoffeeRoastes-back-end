package p2CoffeeRoastesvanquishbackend.services;

import p2CoffeeRoastesvanquishbackend.beans.CreditCard;

public interface CreditCardService {

	public CreditCard addNewCreditCard(CreditCard newCreditCard);

	public CreditCard getLookUpCreditCardByUser(int user_id);

}
