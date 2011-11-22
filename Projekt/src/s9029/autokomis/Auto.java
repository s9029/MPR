package s9029.autokomis;

public class Auto {

	private String marka;
	private TypAuta type;
	private String model;
	private boolean dostepny;
	private float cena;

	public Auto(String marka, TypAuta type, String director, float price) {
		super();
		this.marka = marka;
		this.type = type;
		this.model = director;
		this.dostepny = true;
		this.cena = price;
	}

	public String pobierzMarke() {
		return marka;
	}

	public void setTitle(String title) {
		this.marka = title;
	}

	public TypAuta pobierzTyp() {
		return type;
	}

	public void ustawTyp(TypAuta type) {
		this.type = type;
	}

	public String pobierzModel() {
		return model;
	}

	public void ustalModel(String director) {
		this.model = director;
	}

	public boolean isAvailable() {
		return dostepny;
	}

	public void setAvailable(boolean available) {
		this.dostepny = available;
	}

	public void setPrice(float price) {
		this.cena = price;
	}

	public float getPrice() {
		return cena;
	}

	@Override
	public String toString() {
		return "Auto [marka=" + marka + ", typ=" + type + ", model="
				+ model + ", dostepność=" + dostepny + ", cena=" + cena
				+ "]";
	}

	

}
