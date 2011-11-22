package s9029.autokomis;

public class BrakPieniedzyWyjatek extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BrakPieniedzyWyjatek() {
		super("Klient nie ma pieniedzy aby zaplacic za film");
	}

}
