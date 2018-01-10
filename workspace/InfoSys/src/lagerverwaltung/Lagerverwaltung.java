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

public class Lagerverwaltung {
	
	private PrintWriter out;
	private Set<String> berechtigteMitarbeiter;
	private Set<Lagerposten> lagerbestand;
	
	public Lagerverwaltung()
	{
		berechtigteMitarbeiter = new HashSet<String>();
		lagerbestand = new HashSet<Lagerposten>();
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm");
			Date date = new Date();
			out = new PrintWriter("Log_vom_"+dateFormat.format(date)+".txt");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void berechtigungErteilen(Mitarbeiter mitarbeiter)
	{
		berechtigteMitarbeiter.add(mitarbeiter.getId());
		logEintrag("Lagerrechte wurden an Mitarbeiter \"" +  mitarbeiter.getName() + "\" mit der ID " + mitarbeiter.getId() + " vergeben.", true);
		
	}
	
	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter)
	{
		berechtigteMitarbeiter.remove(mitarbeiter.getId());
		logEintrag("Lagerrechte wurden Mitarbeiter \"" +  mitarbeiter.getName() + "\" mit der ID " + mitarbeiter.getId() + " entzogen.", true);
	}
	
	public void lagerbestandAusgeben()
	{
		for (Lagerposten lagerposten: lagerbestand)
		{
			System.out.println(lagerposten.getArtikel().getId()+ " - " + lagerposten.getArtikel().getName()+ " - "+lagerposten.getLagerbestand()+" Stück "+ lagerposten.getPreis());
		}
		System.out.println();
	}
	
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
			logEintrag("Mitarbeiter " + mitarbeiter.getId() + " hat " + anzahl + "x " + artikel.getName() + " mit der ID " + artikel.getId() + " zum Preis von jeweils " + preis + " Euro hinzugefuegt.", true);
		}
		else
		{
			logEintrag("Mitarbeiter " + mitarbeiter.getId() + " hat versucht, Artikel zum Lager hinzuzufuegen. Der Zugriff wurde verweigert.", true);
		}
	}
	
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
				logEintrag("Mitarbeiter " + mitarbeiter.getId() + " hat eine Bestellung im Wert von " + " Euro durchgefuehrt. Details wurden in Bestellung_09_..._" + mitarbeiter.getId() + ".txt gespeichert.", true);
				for (Bestellposten bestellposten: bestellpostenListe)
				{
					for (Lagerposten lagerposten: lagerbestand)
					{
						Artikel artikel = lagerposten.getArtikel();
						if (artikel.getId().equals(bestellposten.getArtikelID()))
						{
							lagerposten.entnehmeMenge(bestellposten.getAnzahl());
							//logEintrag(bestellposten.getAnzahl() + "x " + artikel.getName(), false);
						}	
					}
				}
			}
			else
			{
				logEintrag("Eine von Mitarbeiter " + mitarbeiter.getId() + " angefragte Bestellung konnte nicht durchgeführt werden, da sich nicht genuegend Artikel im Lager befinden.", true);
			}
		}
		else
		{
			logEintrag("Mitarbeiter " + mitarbeiter.getId() + " hat versucht, eine Bestellung durchzufuehren. Der Zugriff wurde verweigert.", true);
			System.out.println("Mitarbeiter nicht berechtigt");
		}
		return null;
	}
	
	public void logClose() {
		out.close();
	}
	
	private void logEintrag(String text, boolean printDate) {	
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		Date dateObject = new Date();
		String date = dateFormat.format(dateObject);
		if (printDate)
		{
			text = date + ": " + text;
		}
		else
		{
			for (int i = 0; i < date.length()+4; i++)
			{
				text = " " + text;
			}
		}
		out.println(text);
		System.out.println(text);
	}
}
