package p2CoffeeRoastesvanquishbackend.exceptions;

public class CustomerDoesNotExistException extends Exception{
	
	private static final long serialVersionUID = -6511566985029314986L;
	
	public CustomerDoesNotExistException() {
		super("Customer Does Not Exist.");
	}

}
