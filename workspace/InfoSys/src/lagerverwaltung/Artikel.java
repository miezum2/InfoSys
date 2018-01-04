package lagerverwaltung;

public class Artikel {
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

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
