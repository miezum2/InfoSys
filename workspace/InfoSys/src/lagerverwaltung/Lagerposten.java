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
	 * erstellt neuen Lagerposten mit �bergebenen Eigenschaften
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
	 * liefert Artikel-Objekt zur�ck
	 * @return Artikel-Objekt
	 */
	public Artikel getArtikel() {
		return artikel;
	}

	/**
	 * liefert Lagerbestand zur�ck
	 * @return Lagerbestand
	 */
	public int getLagerbestand() {
		return lagerbestand;
	}
	
	/**
	 * verringert Lagerbestand um �bergebene Anzahl
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
	 * liefert Preis des Artikels zur�ck
	 * @return Preis des Artikels
	 */
	public double getPreis() {
		return preis;
	}
	
	/**
	 * erh�ht Lagerbestand um �bergebene Anzahl
	 * @param anzahl Anzahl der hinzuzuf�genden Artikel
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
