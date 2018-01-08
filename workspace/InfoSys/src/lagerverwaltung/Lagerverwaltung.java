package lagerverwaltung;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lagerverwaltung {
	
	private Set<String> berechtigteMitarbeiter;
	private Set<Lagerposten> lagerbestand;
	
	public Lagerverwaltung()
	{
		berechtigteMitarbeiter = new HashSet<String>();
		lagerbestand = new HashSet<Lagerposten>();
	}
	
	public void berechtigungErteilen(Mitarbeiter mitarbeiter)
	{
		berechtigteMitarbeiter.add(mitarbeiter.getId());
	}
	
	public void berechtigungZurueckziehen(Mitarbeiter mitarbeiter)
	{
		berechtigteMitarbeiter.remove(mitarbeiter.getId());
	}
	
	public void lagerbestandAusgeben()
	{
		
	}
	
	public void wareneingangBuchen(Mitarbeiter mitarbeiter, Artikel artikel, int anzahl, double preis)
	{
		if (berechtigteMitarbeiter.contains(mitarbeiter.getId()))
		{
			lagerbestand.add(new Lagerposten(artikel, anzahl, preis));
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
	
	public void printLagerbestand()
	{
		for (Lagerposten lagerposten: lagerbestand)
		{
			System.out.println(lagerposten.getArtikel().getId()+ " - " + lagerposten.getArtikel().getName()+ " - "+lagerposten.getLagerbestand()+" Stück");
		}
		System.out.println();
	}
}
