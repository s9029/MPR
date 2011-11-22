package s9029.autokomis;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class AutoKomis {

	private float totalCashInAutoKomis = 0;

	private static Logger logger = Logger.getLogger(AutoKomis.class);

	private List<Auto> autoList;

	public AutoKomis() {
		autoList = new ArrayList<Auto>();
		logger.debug("Stworzono nowy komis samochodowy");
	}

	public void usunAutoZKomisu(Auto auto) {
		autoList.remove(auto);
		logger.debug("Usunieto samochod z komisu: " + auto.getTitle());
	}
	
	public void ustalNowaCene(String movieTitle, float newPrice){
		Auto auto = znajdzAutoPoModelu(movieTitle);
		auto.setPrice(newPrice);
	}

	public void dodajAuto(Auto auto) {
		autoList.add(auto);
		logger.debug("Dodano nowy samochód do komisu: " + auto.getTitle());
	}

	public void pokazWszystkieAuta() {
		logger.info("Lista wszystkich aut w komisie");
		for (Auto auto : autoList) {
			logger.info(auto.toString());
		}
	}

	public List<Auto> znajdzAutoPoModelu2(String director) {
		List<Auto> resultList = new ArrayList<Auto>();
		for (Auto auto : autoList) {
			if (auto.pobierzModel().equals(director))
				resultList.add(auto);
		}
		return resultList;
	}

	public List<Auto> znajdzAutoPoTypie(String type) {
		List<Auto> resultList = new ArrayList<Auto>();
		for (Auto auto : autoList) {
			if (auto.pobierzTyp().equals(TypAuta.valueOf(type)))
				resultList.add(auto);
		}
		return resultList;
	}

	public Auto znajdzAutoPoModelu(String title) {
		for (Auto auto : autoList) {
			if (auto.getTitle().equals(title)) {
				return auto;
			}
		}
		return null;
	}

	public void kupAuto(Klient klient, Auto Fiesta)
			throws BrakPieniedzyWyjatek {
		if (Fiesta.isAvailable()) {
			float moneyFromCustomer;
			moneyFromCustomer = klient.payMoney(Fiesta.getPrice());
			totalCashInAutoKomis = totalCashInAutoKomis + moneyFromCustomer;
			Fiesta.setAvailable(false);
			klient.pokazAuto(Fiesta);
			logger.debug("Klient " + klient.getName()
					+ " wlasnie wykupil samochod " + Fiesta.getTitle());
		}
	}
}
