package lagerverwaltung;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lagerverwaltung {
	
	private Set<String> berechtigteMitarbeiter;
	private List<Lagerposten> lagerbestand;
	
	public Lagerverwaltung()
	{
		berechtigteMitarbeiter = new HashSet<String>();
		lagerbestand = new ArrayList<Lagerposten>();
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
				System.out.println("Bestellung ausgef�hrt");
			}
			else
			{
				System.out.println("Ausf�hrung nicht m�glich");
			}
		}
		else
		{
			System.out.println("Mitarbeiter nicht berechtigt");
		}
		return null;
	}
}
