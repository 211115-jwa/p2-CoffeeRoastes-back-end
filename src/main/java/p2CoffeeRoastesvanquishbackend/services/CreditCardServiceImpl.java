package p2CoffeeRoastesvanquishbackend.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p2CoffeeRoastesvanquishbackend.beans.CreditCard;
import p2CoffeeRoastesvanquishbackend.data.CreditCardRepository;

@Service

public class CreditCardServiceImpl implements CreditCardService {

	private CreditCardRepository creditCardRepo;

	@Autowired
	public CreditCardServiceImpl(CreditCardRepository CreditCardRepo) {
		this.creditCardRepo = CreditCardRepo;
	}

	@Override
	public CreditCard addNewCreditCard(CreditCard newCreditCard) {

		CreditCard creditCardAdded = creditCardRepo.save(newCreditCard);
		if (creditCardAdded != null) {
			return creditCardAdded;
		} else {
			return null;
		}
	}

	@Override
	public Set<CreditCard> getLookUpCreditCardByUser(int user_id) {

		Set <CreditCard> UserByCreditCard = creditCardRepo.findByUserId(user_id);
		if (UserByCreditCard != null) {
			creditCardRepo.findById(user_id);
		}
		return UserByCreditCard;
	}

}