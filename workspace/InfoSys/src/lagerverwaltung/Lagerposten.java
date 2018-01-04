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

	public Artikel getArtikel() {
		return artikel;
	}

	public int getLagerbestand() {
		return lagerbestand;
	}

	public double getPreis() {
		return preis;
	}
}
