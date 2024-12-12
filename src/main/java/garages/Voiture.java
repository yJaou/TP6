package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage
	 * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		if (myStationnements.isEmpty() || !myStationnements.get(myStationnements.size() - 1).estEnCours()) {
			Stationnement s = new Stationnement(this, g);
			myStationnements.add(s);
		} else {
			throw new java.lang.Exception("voiture deja dans un garage");
		}
	}


	/**
	 * Fait sortir la voiture du garage
	 * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if (!myStationnements.isEmpty() && myStationnements.get(myStationnements.size() - 1).estEnCours()) {
			myStationnements.get(myStationnements.size() - 1).terminer();
			// Trouver le dernier stationnement de la voiture
			// Terminer ce stationnement
		}else {
			throw new java.lang.Exception("La voiture n'est pas dans un garage");
		}
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		Set<Garage> visitedGarage = new HashSet<Garage>();
		for (Stationnement s : myStationnements) {
			visitedGarage.add(s.getGarageVisite());
		}
		return visitedGarage;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		return (!myStationnements.isEmpty()) && myStationnements.get(myStationnements.size() - 1).estEnCours();
		// Vrai si le dernier stationnement est en cours
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des
	 * dates d'entrée / sortie dans ce garage
	 * <br>
	 * Exemple :
	 *
	 * <pre>
	 * Garage Castres:
	 * 		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * 		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 * 		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		// TODO: Implémenter cette méthode
		HashSet<Garage> garagesList = (HashSet<Garage>) this.garagesVisites();

		for (Garage g : garagesList){
			out.println(g.toString());
			for (Stationnement s : myStationnements){
				if (s.getGarageVisite().equals(g)){
					out.println(s.toString());
				}
			}
		}
		// Utiliser les méthodes toString() de Garage et Stationnement
	}

}