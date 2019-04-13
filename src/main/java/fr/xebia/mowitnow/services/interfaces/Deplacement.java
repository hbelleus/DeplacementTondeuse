package fr.xebia.mowitnow.services.interfaces;

import fr.xebia.mowitnow.models.Tondeuse;

public interface Deplacement {

	/**
	 * Méthode de déplacement de la tondeuse. Met à jour la position avec la
	 * position calculée.
	 *
	 * @param tondeuse
	 * @param orientation
	 * @return Integer
	 */
	Tondeuse seDeplacer(Tondeuse tondeuseAvantCmd, Tondeuse tondeuseApresCmd);

	/**
	 * Deplacement selon l'axe des abscisses x.
	 *
	 * @param x
	 * @param orientation
	 * @return Integer
	 */
	Integer seDeplacerSelonX(Integer x, double orientation);

	/**
	 * Deplacement selon l'axe des abscisses y.
	 *
	 * @param y
	 * @param orientation
	 * @return Integer
	 */
	Integer seDeplacerSelonY(Integer y, double orientation);
}
