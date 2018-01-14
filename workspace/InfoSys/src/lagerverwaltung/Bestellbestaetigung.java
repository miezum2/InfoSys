package lagerverwaltung;

/**
 * Klasse zur Erzeugung von Bestellbest�tigungen
 *
 */
public class Bestellbestaetigung {
	
	private final boolean ausgefuehrt;
	private final double gesamtpreis;
	
	/**
	 * erstellt neue Bestellbest�tigung mit den �bergebenen Eigenschaften
	 * @param ausgefuehrt konnte die Bestellung ausgef�hrt werden?
	 * @param gesamtpreis gesamtpreis der Bestellung
	 */
	public Bestellbestaetigung(boolean ausgefuehrt, double gesamtpreis)
	{
		this.ausgefuehrt = ausgefuehrt;
		this.gesamtpreis = gesamtpreis;
	}
}
