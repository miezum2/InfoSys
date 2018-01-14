package lagerverwaltung;

import java.util.ArrayList;
import java.util.List;

/**
 * Hauptklasse des Programms
 *
 */
public class Start {
	
	private static Lagerverwaltung lager;
	
	/**
	 * erste Methode, die beim Programmstart vom System aufgerufen wird
	 */
	public static void main(String[] args)
	{
		
		
		lager = new Lagerverwaltung();
		
		Mitarbeiter mitarbeiter0 = new Mitarbeiter("0000", "Manfred");
		Mitarbeiter mitarbeiter1 = new Mitarbeiter("0001", "Sandra");
		Mitarbeiter mitarbeiter2 = new Mitarbeiter("0002", "Paul");
		
		lager.berechtigungErteilen(mitarbeiter0);
		lager.berechtigungErteilen(mitarbeiter1);
		lager.berechtigungErteilen(mitarbeiter2);
		lager.berechtigungZurueckziehen(mitarbeiter2);
		
		Artikel artikel0 = new Artikel("00000", "Apfel", "sauer");
		Artikel artikel1 = new Artikel("00001", "Birne", "gelb");
		Artikel artikel2 = new Artikel("00002", "Clementine", "orange");
		
		lager.wareneingangBuchen(mitarbeiter0, artikel0, 20, 0.5);
		lager.wareneingangBuchen(mitarbeiter0, artikel2, 7, 1);
		lager.wareneingangBuchen(mitarbeiter0, artikel1, 10, 0.8);
		lager.wareneingangBuchen(mitarbeiter1, artikel1, 15, 0.9);
		lager.wareneingangBuchen(mitarbeiter2, artikel2, 7, 1);
		
		lager.lagerbestandAusgeben();
		List<Bestellposten> bestellpostenListe = new ArrayList<Bestellposten>();
		bestellpostenListe.add(new Bestellposten("00000", 20));
		bestellpostenListe.add(new Bestellposten("00001", 5));
		
		lager.bestellungAusfuehren(mitarbeiter0, bestellpostenListe);		
		lager.lagerbestandAusgeben();
		
		lager.bestellungAusfuehren(mitarbeiter2, bestellpostenListe);
		
		lager.logClose();
	
	}
}
