package lagerverwaltung;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
			out = new PrintWriter("text.txt");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void berechtigungErteilen(Mitarbeiter mitarbeiter)
	{
		berechtigteMitarbeiter.add(mitarbeiter.getId());
		out.println("Die Rechte wurden an " +  mitarbeiter.getName() + " vergeben.");
		
	}
	
	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter)
	{
		berechtigteMitarbeiter.remove(mitarbeiter.getId());
		out.println("Die Rechte wurden " +  mitarbeiter.getName() + " entzogen.");
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
			out.println(anzahl + " " + artikel.getName() + " zum Preis von jeweils " + preis + " Euro wurden hinzugefuegt.");
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
				for (Bestellposten bestellposten: bestellpostenListe)
				{
					for (Lagerposten lagerposten: lagerbestand)
					{
						Artikel artikel = lagerposten.getArtikel();
						if (artikel.getId().equals(bestellposten.getArtikelID()))
						{
							lagerposten.entnehmeMenge(bestellposten.getAnzahl());
						}	
					}
				}
				System.out.println("Bestellung ausgeführt");
			}
			else
			{
				System.out.println("Ausführung nicht möglich");
			}
		}
		else
		{
			System.out.println("Mitarbeiter nicht berechtigt");
		}
		return null;
	}
	
	public void logClose() {
		out.close();
	}
	
	private void logEintrag(String text) {		
		out.println(text = "");
	}
}
