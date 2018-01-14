package lagerverwaltung;

/**
 * Klasse zur Erstellung von Bestellposten
 *
 */
public class Bestellposten {
	
	private final String artikelID;
	private final int anzahl;
	
	/**
	 * erstellt neuen Bestellposten mit den übergebenen Eigenschaften
	 * @param artikelID ID des bestellten Artikels
	 * @param anzahl Anzahl der Artikel
	 */
	public Bestellposten(String artikelID, int anzahl)
	{
		this.artikelID = artikelID;
		this.anzahl = anzahl;
	}

	/**
	 * liefert ID des Artikels zurück
	 * @return ID des Artikels
	 */
	public String getArtikelID() {
		return artikelID;
	}
	
	/**
	 * liefert Anzahl der bestellten Artikel zurück
	 * @return Anzahl der bestellten Artikel
	 */
	public int getAnzahl() {
		return anzahl;
	}
	
	
	
}
