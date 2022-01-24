package p2CoffeeRoastesvanquishbackend.exceptions;

public class IncorrectAddressExeption extends Exception{
	
	private static final long serialVersionUID = -6511566985029314986L;
	
	public IncorrectAddressExeption() {
		super("Enter a correct address.");
	}

}
