package lagerverwaltung;

/**
 * Klasse zur Erstellung von Bestellposten
 *
 */
public class Bestellposten {
	
	private final String artikelID;
	private final int anzahl;
	
	/**
	 * erstellt neuen Bestellposten mit den �bergebenen Eigenschaften
	 * @param artikelID ID des bestellten Artikels
	 * @param anzahl Anzahl der Artikel
	 */
	public Bestellposten(String artikelID, int anzahl)
	{
		this.artikelID = artikelID;
		this.anzahl = anzahl;
	}

	/**
	 * liefert ID des Artikels zur�ck
	 * @return ID des Artikels
	 */
	public String getArtikelID() {
		return artikelID;
	}
	
	/**
	 * liefert Anzahl der bestellten Artikel zur�ck
	 * @return Anzahl der bestellten Artikel
	 */
	public int getAnzahl() {
		return anzahl;
	}
	
	
	
}
