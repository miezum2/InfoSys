package lagerverwaltung;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Klasse zur Verwaltung eines Lagers
 *
 */
public class Lagerverwaltung {
	
	private PrintWriter out;
	private Set<String> berechtigteMitarbeiter;
	private Set<Lagerposten> lagerbestand;
	
	/**
	 * erstellt neues Lagerverwaltungs-Objekt
	 */
	public Lagerverwaltung()
	{
		berechtigteMitarbeiter = new HashSet<String>();
		lagerbestand = new HashSet<Lagerposten>();
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm");
			Date date = new Date();
			out = new PrintWriter("Log_"+dateFormat.format(date)+".txt");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * erteilt Rechte zur Nutzung der Lagerverwaltung an einen Mitarbeiter
	 * @param mitarbeiter Mitarbeiter, der die Rechte erhält
	 */
	public void berechtigungErteilen(Mitarbeiter mitarbeiter)
	{
		berechtigteMitarbeiter.add(mitarbeiter.getId());
		logEintrag("Lagerrechte wurden an Mitarbeiter " + mitarbeiter.getId() + " (" +  mitarbeiter.getName() + ") vergeben.");
		
	}
	
	/**
	 * entzieht einem Nutzer die Rechte zur Datenbankverwaltung
	 * @param mitarbeiter Mitarbeiter, dem die Rechte entzogen werden sollen
	 */
	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter)
	{
		berechtigteMitarbeiter.remove(mitarbeiter.getId());
		logEintrag("Lagerrechte wurden Mitarbeiter " + mitarbeiter.getId() +  " (" +mitarbeiter.getName() + ") entzogen.");
	}
	
	/**
	 * schreibt Informationen über alle Artikel im Lagerbestand in die Konsole
	 */
	public void lagerbestandAusgeben()
	{
		System.out.println("Aktueller Lagerbestand:");
		for (Lagerposten lagerposten: lagerbestand)
		{
			System.out.println("  ID: " + lagerposten.getArtikel().getId()+ ", Name: " + lagerposten.getArtikel().getName()+ ", Anzahl: "+lagerposten.getLagerbestand()+" Stück, Preis: je "+ lagerposten.getPreis()+ " Euro");
		}
	}
	
	/**
	 * fügt einen neuen Artikel zum Lagerbestand hinzu oder aktualisiert die Menge und den Preis
	 * @param mitarbeiter Mitarbeiter, der den Wareneingang durchführt
	 * @param artikel Artikel, der  hinzugefügt werden soll
	 * @param anzahl Anzahl der hinzuzufügenden Artikel
	 * @param preis Preis des Artikels
	 */
	public void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int anzahl, double preis)
	{
		if (berechtigteMitarbeiter.contains(mitarbeiter.getId()))
		{
			boolean mengeaufaddiert = false;
			for (Lagerposten lagerposten : lagerbestand)
			{
				String id = lagerposten.getArtikel().getId();
				
				if (id.equals(artikel.getId()))
				{
					lagerposten.addiereMenge(anzahl);
					lagerposten.setPreis(preis);
					mengeaufaddiert = true;
				}
				
			}
			if(!mengeaufaddiert) {
				lagerbestand.add(new Lagerposten(artikel, anzahl, preis));
			}
			logEintrag("Mitarbeiter " + mitarbeiter.getId() + " hat " + anzahl + "x " + artikel.getName() + " mit der ID " + artikel.getId() + " zum Preis von jeweils " + preis + " Euro hinzugefuegt.");
		}
		else
		{
			logEintrag("Mitarbeiter " + mitarbeiter.getId() + " hat versucht, Artikel zum Lager hinzuzufuegen. Der Zugriff wurde verweigert.");
		}
	}
	
	/**
	 * führt eine Bestellung durch und entfernt die Artikel aus dem Lager
	 * @param mitarbeiter Mitarbeiter, der die Bestellung durchführt
	 * @param bestellpostenListe Liste mit allen Bestellposten
	 * @return Informationen über die Bestellung
	 */
	public Bestellbestaetigung bestellungAusfuehren(Mitarbeiter mitarbeiter, List<Bestellposten> bestellpostenListe)
	{
		if (berechtigteMitarbeiter.contains(mitarbeiter.getId()))
		{
			boolean bestellungMoeglich = true;
			for (Bestellposten bestellposten: bestellpostenListe)
			{
				boolean artikelEnthalten = false;
				for (Lagerposten lagerposten: lagerbestand)
				{
					Artikel artikel = lagerposten.getArtikel();
					if (artikel.getId().equals(bestellposten.getArtikelID()) && lagerposten.getLagerbestand() >= bestellposten.getAnzahl())
					{
						artikelEnthalten = true;
					}					
				}
				if (!artikelEnthalten)
				{
					bestellungMoeglich = false;
				}
			}
			if (bestellungMoeglich)
			{
				double gesamtpreis = 0;				
				for (Bestellposten bestellposten: bestellpostenListe)
				{
					for (Lagerposten lagerposten: lagerbestand)
					{
						Artikel artikel = lagerposten.getArtikel();
						if (artikel.getId().equals(bestellposten.getArtikelID()))
						{
							lagerposten.entnehmeMenge(bestellposten.getAnzahl());
							gesamtpreis += lagerposten.getPreis()*bestellposten.getAnzahl();
						}	
					}
				}
				logEintrag("Mitarbeiter " + mitarbeiter.getId() + " hat eine Bestellung im Wert von " + gesamtpreis + " Euro durchgefuehrt.");
				return new Bestellbestaetigung(true, gesamtpreis);
			}
			else
			{
				logEintrag("Eine von Mitarbeiter " + mitarbeiter.getId() + " angefragte Bestellung konnte nicht durchgeführt werden, da sich nicht genuegend Artikel im Lager befinden.");
				return new Bestellbestaetigung(false, -1);
			}
		}
		else
		{
			logEintrag("Mitarbeiter " + mitarbeiter.getId() + " hat versucht, eine Bestellung durchzufuehren. Der Zugriff wurde verweigert.");
			return new Bestellbestaetigung(false, -1);
		}
	}
	
	/**
	 * speichert und schließt die Log-Datei
	 */
	public void logClose() {
		out.close();
	}
	
	/**
	 * schreibt einen neuen Logeintrag in die Log-Datei (und in die Konsole)
	 * @param text zu schreibender Text
	 */
	private void logEintrag(String text) {	
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Date dateObject = new Date();
		String date = dateFormat.format(dateObject);
		text = date + ": " + text;
		
		out.println(text);
		System.out.println(text);
	}
}
