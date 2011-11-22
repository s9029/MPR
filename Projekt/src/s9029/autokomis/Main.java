package s9029.autokomis;

import org.apache.log4j.Logger;


public class Main {

	private static Logger logger= Logger.getLogger(Main.class);
	
	public static void main(String[] args) {

		AutoKomis autoKomis = new AutoKomis();
		
		autoKomis.dodajAuto(new Auto("BMW",TypAuta.Hatchback,"316",15000));
		autoKomis.dodajAuto(new Auto("Ford",TypAuta.Hatchback,"Fiesta",3990));
		autoKomis.dodajAuto(new Auto("Fiat",TypAuta.Hatchback,"Punto",9900));
		autoKomis.dodajAuto(new Auto("Volkswagen",TypAuta.Kombi,"Passat",10800));
		autoKomis.dodajAuto(new Auto("Skoda",TypAuta.Kombi,"Fabia",15900));
		autoKomis.dodajAuto(new Auto("Ford",TypAuta.VAN,"Galaxy",23400));

		autoKomis.pokazWszystkieAuta();
		
		try {
			Klient klient1 = new Klient("Adam Nowak", -10000);
		
		}catch (NiepoprawnaIloscPieniedzy exception) {
			logger.error(exception.getMessage());
		}
		
		Klient klient2 = null;
		try {
			klient2 = new Klient("Jan Kowalski", 1000);
		} catch (NiepoprawnaIloscPieniedzy e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		klient2.pokazMojeAuta();
		
		Auto fiestaAuto = autoKomis.znajdzAutoPoMarce("Fiesta");
		Auto puntoAuto = autoKomis.znajdzAutoPoMarce("Punto");
		Auto galaxyAuto = autoKomis.znajdzAutoPoMarce("Galaxy");
		try{	
			autoKomis.kupAuto(klient2 , fiestaAuto);
			autoKomis.kupAuto(klient2 , puntoAuto);
			autoKomis.kupAuto(klient2 , galaxyAuto);
		} catch (BrakPieniedzyWyjatek e) {
			logger.error(e.getMessage());
		}
		
		klient2.pokazMojeAuta();
		
		autoKomis.pokazWszystkieAuta();
		
	}

}
