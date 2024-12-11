package garages;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.PrintStream;
import java.util.*;


/**
 * Représente une voiture qui peut être stationnée dans des garages.
 */
@RequiredArgsConstructor
@ToString
public class Voiture {

	@Getter
	@NonNull
	private final String immatriculation;
	@ToString.Exclude // On ne veut pas afficher les stationnements dans toString
	private final List<Stationnement> myStationnements = new LinkedList<>();

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws IllegalStateException Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		if (estDansUnGarage()) {
			throw new Exception("La voiture est déjà dans un garage");
		}

		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}
	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws IllegalStateException si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if (!estDansUnGarage()) {
			throw new Exception("La voiture n'est pas dans un garage");
		}

		// Trouver le dernier stationnement de la voiture
		Stationnement dernierStationnement = myStationnements.get(myStationnements.size() - 1);

		// Terminer ce stationnement
		dernierStationnement.terminer();
	}
	/**
	 * Calcule l'ensemble des garages visités par cette voiture
	 * 
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		Set<Garage> garagesVisités = new HashSet<>();

		// Parcourir tous les stationnements de la voiture
		for (Stationnement stationnement : myStationnements) {
			garagesVisités.add(stationnement.getGarage());
		}

		return garagesVisités;
	}


	/**
	 * Détermine si la voiture est actuellement dans un garage
	 * 
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// TODO: Implémenter cette méthode
		// Vrai si il y a des stationnements et le dernier stationnement est en cours
		return !myStationnements.isEmpty() && myStationnements.get(myStationnements.size() - 1).estEnCours();
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * stationnements dans ce garage
	 * <br>
	 * Exemple :
	 * 
	 * <pre>
	 * Garage(name=Universite Champollion Albi):
	 * 		Stationnement{ entree=13/11/2024, sortie=13/11/2024 }
	 * Garage(name=ISIS Castres):
	 * 		Stationnement{ entree=13/11/2024, sortie=13/11/2024 }
	 * 		Stationnement{ entree=13/11/2024, en cours }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out pour imprimer dans la
	 *            console)
	 */

	public void imprimeStationnements(PrintStream out) {
		// TODO: Implémenter cette méthode
		for (Garage garage : garagesVisites()) {
			out.println(garage + ":");
			for (Stationnement stationnement : myStationnements) {
				if (stationnement.getGarage().equals(garage)) {
					out.println("\t" + stationnement);
				}
			}
		}
	}

}
