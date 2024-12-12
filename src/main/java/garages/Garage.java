package garages;

/**
 * Représente un garage avec un nom.
 */
public class Garage {

	// Le nom du garage
	private String name;

	/**
	 * Constructeur de la classe `Garage`.
	 *
	 * @param s le nom du garage (ne peut pas être null)
	 */
	public Garage(String s) {
		name = s;
	}

	/**
	 * Retourne le nom du garage.
	 *
	 * @return le nom du garage
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom du garage.
	 *
	 * @param name le nouveau nom du garage (ne peut pas être null)
	 * @throws IllegalArgumentException si le nom est null
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name is null");
		}
		this.name = name;
	}

	/**
	 * Retourne une représentation en chaîne de caractères de l'objet `Garage`.
	 *
	 * @return une chaîne de caractères représentant le garage, incluant son nom
	 */
	@Override
	public String toString() {
		return "Garage " + name;
	}
}