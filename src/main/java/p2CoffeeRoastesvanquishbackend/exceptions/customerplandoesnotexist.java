package p2CoffeeRoastesvanquishbackend.exceptions;

public class customerplandoesnotexist extends Exception{
	
	private static final long serialVersionUID = -6511566985029314986L;
	
	public customerplandoesnotexist() {
		super("Customer Plan Does Not Exist.");
	}

}
