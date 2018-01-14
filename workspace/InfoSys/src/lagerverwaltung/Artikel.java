package lagerverwaltung;

/**
 * Klasse zur Speicherung von Artikeleigenschaften
 *
 */
public class Artikel {
	private final String id;
	private String name;
	private String beschreibung;
	
	/**
	 * erstellt neuen Artikel mit den �bergebenen Eigenschaften
	 * @param id ID des Artikels
	 * @param name Name des Artikels
	 * @param beschreibung Beschreibung des Artikels
	 */
	public Artikel(String id, String name, String beschreibung)
	{
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
	}
	
	/**
	 * liefert ID des Artikels zur�ck
	 * @return ID des Artikels
	 */
	public String getId() {
		return id;
	}

	/**
	 * liefert Namen des Artikels zur�ck 
	 * @return Name des Artikels (wer h�tte das gedacht?)
	 */
	public String getName() {
		return name;
	}
}
