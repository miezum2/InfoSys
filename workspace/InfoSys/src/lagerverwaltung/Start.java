package lagerverwaltung;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Start {
	
	private static Lagerverwaltung lager;
	
	
	public static void main(String[] args)
	{
		
		
		lager = new Lagerverwaltung();
		
		Mitarbeiter mitarbeiter0 = new Mitarbeiter("0000", "Manfred");
		Mitarbeiter mitarbeiter1 = new Mitarbeiter("0001", "Sandra");
		
		lager.berechtigungErteilen(mitarbeiter0);
		lager.berechtigungErteilen(mitarbeiter1);
		lager.berechtigungZurueckziehen(mitarbeiter1);
		
		Artikel artikel0 = new Artikel("00000", "Apfel", "sauer");
		Artikel artikel1 = new Artikel("00001", "Birne", "gelb");
		
		lager.wareneingangBuchen(mitarbeiter0, artikel0, 20, 0.5);
		lager.wareneingangBuchen(mitarbeiter0, artikel1, 10, 0.8);
		lager.wareneingangBuchen(mitarbeiter0, artikel1, 15, 0.9);
		
		lager.lagerbestandAusgeben();
		List<Bestellposten> bestellpostenListe = new ArrayList<Bestellposten>();
		bestellpostenListe.add(new Bestellposten("00000", 20));
		bestellpostenListe.add(new Bestellposten("00001", 5));
		
		lager.bestellungAusfuehren(mitarbeiter0, bestellpostenListe);		
		lager.lagerbestandAusgeben();
		
		lager.logClose();
	
	}
}
