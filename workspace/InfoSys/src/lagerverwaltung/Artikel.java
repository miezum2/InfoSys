package lagerverwaltung;

public class Artikel {
	
	private final String id;
	private String name;
	private String beschreibung;
	
	public Artikel(String id, String name, String beschreibung)
	{
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
	}
}
