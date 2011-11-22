package s9029.autokomis.tests;

import java.util.List;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import s9029.autokomis.Klient;
import s9029.autokomis.NiepoprawnaIloscPieniedzy;
import s9029.autokomis.TypAuta;
import s9029.autokomis.Auto;
import s9029.autokomis.BrakPieniedzyWyjatek;
import s9029.autokomis.AutoKomis;


public class AutoKomisTest {

	private AutoKomis autoKomis;
	
	@Before
	public void setUp() throws Exception {
		autoKomis = new AutoKomis();
		autoKomis.dodajAuto(new Auto("BMW",TypAuta.Hatchback,"316",15000));
		autoKomis.dodajAuto(new Auto("Ford",TypAuta.Hatchback,"Fiesta",3990));
		autoKomis.dodajAuto(new Auto("Fiat",TypAuta.Hatchback,"Punto",9900));
		autoKomis.dodajAuto(new Auto("Volkswagen",TypAuta.Kombi,"Passat",10800));
		autoKomis.dodajAuto(new Auto("Volkswagen",TypAuta.Hatchback,"Passat",8600));
		autoKomis.dodajAuto(new Auto("Volkswagen",TypAuta.Kombi,"Golf",15900));		
		autoKomis.dodajAuto(new Auto("Skoda",TypAuta.Kombi,"Fabia",15900));
		autoKomis.dodajAuto(new Auto("Ford",TypAuta.VAN,"Galaxy",23400));
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void pokazWszystkieAutaWKomisieTest(){
		autoKomis.pokazWszystkieAuta();
	}
	
	@Test
	public void usunAutoZKomisuTest(){
		Auto passat = autoKomis.znajdzAutoPoMarce("Passat");
		autoKomis.pokazWszystkieAuta();
		autoKomis.usunAutoZKomisu(passat);
		autoKomis.pokazWszystkieAuta();
	}
	
	@Test
	public void kupAutoTest(){
		Klient klient = null;
		try {
			klient = new Klient("Adam Nowak", 10000);
		} catch (NiepoprawnaIloscPieniedzy e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Auto Fabia = autoKomis.znajdzAutoPoMarce("Fabia");
		
		try {
			autoKomis.kupAuto(klient, Fabia);
			System.out.println("Sprawdzanie czy kupione auto ma FLAGE AVAILABLE=FALSE");
			boolean available = Fabia.isAvailable();
			Assert.assertFalse("BLAD! auto mimo wykupu ma flage dostepnosci = true", available);
			System.out.println("Udalo sie kupic auto " + Fabia.pobierzMarke());
		} catch (BrakPieniedzyWyjatek e) {
			e.printStackTrace();
			Assert.fail("Wystapil wyjatek w czasie proby kupienia auta: " + e.getMessage());
		}
	}
	
	@Test
	public void settingPriceTest(){
		float newPrice = 555;
		autoKomis.ustalNowaCene("Dr House", newPrice);
		Auto auto = autoKomis.znajdzAutoPoMarce("Fabia");
		Assert.assertEquals("BLAD, Nie udalo sie ustawic nowej ceny dla filmu ", auto.getPrice() ,newPrice);
		
	}

	
	@Test
	public void znajdzPoModeluTest(){
		System.out.println("Wyszukiwanie po modelu...");
		List<Auto> resultList = autoKomis.znajdzAutoPoModelu("Jan Nowak");
		Assert.assertNotNull("Blad, lista zwrocona z metody wyszukujacej po modelu zwrocila NULL",resultList);
		Assert.assertTrue("Blad, lista zwrocona z metody wyszukujacej po modelu jest pusta",resultList.size()>0);
		if(resultList.size()>0){
			for (int i = 0; i < resultList.size(); i++) {
				System.out.println("Znaleziono " + resultList.get(i).toString());
			}
		}
	}
	
	@Test
	public void findByDataCarrierTypeTest(){
		System.out.println("Wyszukiwanie po typie auta...");
		List<Auto> resultList = autoKomis.znajdzAutoPoTypie("VAN");
		Assert.assertNotNull("Blad, lista zwrocona z metody wyszukujacej po typie auta zwrocila NULL",resultList);
		Assert.assertTrue("Blad, lista zwrocona z metody wyszukujacej po typie auta jest pusta",resultList.size()>0);
		if(resultList.size()>0){
			for (int i = 0; i < resultList.size(); i++) {
				System.out.println("Znaleziono " + resultList.get(i).toString());
			}
		}
	}
	
	@Test
	public void znajdzPoMarce(){
		Auto smerfy = autoKomis.znajdzAutoPoMarce("BMW");		
		Assert.assertNotNull("Blad, nie znaleziono auta BMW szukajac po marce",smerfy);		
		if(smerfy!=null)
			System.out.println("Znaleziono auto BMW wyszujujac po marce");
	}
	
	@Test(expected=NiepoprawnaIloscPieniedzy.class)
	public void createCustomerWithNegativeAmountOfMoneyTest() throws NiepoprawnaIloscPieniedzy{
		System.out.println("Test wyjatku InvalidMoneyAmountValue ");
		Klient klient1 = new Klient("Pawel Kowalski", -10000);
	}
	
	@Test(expected=BrakPieniedzyWyjatek.class)
	public void clientHasNoMoneyToPayTest() throws NiepoprawnaIloscPieniedzy, BrakPieniedzyWyjatek{
		System.out.println("Test wyjatku NoMoneyException ");
		Klient klient1 = new Klient("Adam Nowak", 1);
		Auto passat = autoKomis.znajdzAutoPoMarce("Passat");
		Assert.assertNotNull("nie znaleziono auta BMW", passat);
		System.out.println("Znaleziono samochod Passat");
		autoKomis.kupAuto(klient1, passat);
	}
}
