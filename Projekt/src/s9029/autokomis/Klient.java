package s9029.autokomis;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Klient {

	private Logger logger = Logger.getLogger(Klient.class);
	private final String name;
	private float cash;
	private List<Auto> MojeAuta;

	public Klient(String name, float money) throws NiepoprawnaIloscPieniedzy {
		MojeAuta = new ArrayList<Auto>();
		this.name = name;
		if (money < 0) {
			throw new NiepoprawnaIloscPieniedzy();
		}
		this.cash = money;
	}

	
	public String getName() {
		return name;
	}


	public void pokazMojeAuta() {
		logger.info("Lista zakupow klienta o nazwisku: " + name);
		for (Auto auto : MojeAuta) {
			logger.info(auto.toString());
		}
	}

	public void pokazAuto(Auto auto) {
		MojeAuta.add(auto);
	}

	public Auto returnAuto(String title) {
		for (Auto auto : MojeAuta) {
			if (auto.pobierzMarke().equals(title))
				return auto;
		}
		return null;
	}

	public float payMoney(float priceToPay) throws BrakPieniedzyWyjatek {
		if (cash - priceToPay >= 0) { // chce zaplacic jesli mam pieniadze
			cash = cash - priceToPay;
			return priceToPay;
		}
		throw new BrakPieniedzyWyjatek(); // ale jak nie mam pieniedzy to nie place
	}

}
