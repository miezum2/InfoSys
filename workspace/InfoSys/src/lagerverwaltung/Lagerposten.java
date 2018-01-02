package lagerverwaltung;

public class Lagerposten {

	private Artikel artikel;
	private int lagerbestand;
	private double preis;
	
	public Lagerposten(Artikel artikel, int lagerbestand, double preis)
	{
		this.artikel = artikel;
		this.lagerbestand = lagerbestand;
		this.preis = preis;
	}
}
