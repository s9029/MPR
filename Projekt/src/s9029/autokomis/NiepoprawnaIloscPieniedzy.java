package s9029.autokomis;

public class NiepoprawnaIloscPieniedzy extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NiepoprawnaIloscPieniedzy() {
		super("Klient nie moze posiadac ujemnej ilosci pieniedzy");
	}

}
