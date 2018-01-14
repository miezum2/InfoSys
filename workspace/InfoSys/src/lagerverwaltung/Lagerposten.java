package lagerverwaltung;

/**
 * Klasse zur erstellung von Lagerposten
 *
 */
public class Lagerposten {

	private Artikel artikel;
	private int lagerbestand;
	private double preis;
	
	/**
	 * erstellt neuen Lagerposten mit übergebenen Eigenschaften
	 * @param artikel zu lagernder Artikel
	 * @param lagerbestand Anzahl der Artikel
	 * @param preis Preis eines Artikels
	 */
	public Lagerposten(Artikel artikel, int lagerbestand, double preis)
	{
		this.artikel = artikel;
		this.lagerbestand = lagerbestand;
		this.preis = preis;
	}

	/**
	 * liefert Artikel-Objekt zurück
	 * @return Artikel-Objekt
	 */
	public Artikel getArtikel() {
		return artikel;
	}

	/**
	 * liefert Lagerbestand zurück
	 * @return Lagerbestand
	 */
	public int getLagerbestand() {
		return lagerbestand;
	}
	
	/**
	 * verringert Lagerbestand um übergebene Anzahl
	 * @param anzahl Anzahl der zu entnehmenden Artikel
	 */
	public void entnehmeMenge(int anzahl)
	{
		lagerbestand -= anzahl;
		if (lagerbestand < 0)
		{
			System.out.println("Fehler: Der Lagerbestand kann nicht negativ sein");
		}
	}

	/**
	 * liefert Preis des Artikels zurück
	 * @return Preis des Artikels
	 */
	public double getPreis() {
		return preis;
	}
	
	/**
	 * erhöht Lagerbestand um übergebene Anzahl
	 * @param anzahl Anzahl der hinzuzufügenden Artikel
	 */
	public void addiereMenge(int anzahl)
	{
		lagerbestand += anzahl;
	}
	
	/**
	 * setzt den Preis des Artikels
	 * @param preis neuer Preis des Artikels
	 */
	public void setPreis(double preis)
	{
		this.preis = preis;
	}
}
