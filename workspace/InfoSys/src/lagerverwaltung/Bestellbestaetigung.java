package lagerverwaltung;

/**
 * Klasse zur Erzeugung von Bestellbestätigungen
 *
 */
public class Bestellbestaetigung {
	
	private final boolean ausgefuehrt;
	private final double gesamtpreis;
	
	/**
	 * erstellt neue Bestellbestätigung mit den übergebenen Eigenschaften
	 * @param ausgefuehrt konnte die Bestellung ausgeführt werden?
	 * @param gesamtpreis gesamtpreis der Bestellung
	 */
	public Bestellbestaetigung(boolean ausgefuehrt, double gesamtpreis)
	{
		this.ausgefuehrt = ausgefuehrt;
		this.gesamtpreis = gesamtpreis;
	}
}
