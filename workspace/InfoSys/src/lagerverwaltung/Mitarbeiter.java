package lagerverwaltung;

/**
 * Klasse zur Erstellung von Mitarbeitern
 *
 */
public class Mitarbeiter {
	
	private final String id;
	private String name;
	
	/**
	 * erstellt neuen Mitarbeiter mit übergebenen Eigenschaften
	 * @param id ID des Mitarbeiters
	 * @param name Name des Mitarbeiters
	 */
	public Mitarbeiter(String id, String name)
	{
		this.id = id;
		this.name = name;
	}

	/**
	 * liefert Namen des Mitarbeiters zurück
	 * @return Namen des Mitarbeiters
	 */
	public String getName() {
		return name;
	}

	/**
	 * liefert ID des Mitarbeiters zurück
	 * @return ID des Mitarbeiters
	 */
	public String getId() {
		return id;
	}
}
