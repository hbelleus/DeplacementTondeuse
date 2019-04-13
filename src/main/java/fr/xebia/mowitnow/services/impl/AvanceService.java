package fr.xebia.mowitnow.services.impl;

import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.models.services.PerimetreService;
import fr.xebia.mowitnow.services.interfaces.Action;
import fr.xebia.mowitnow.services.interfaces.Avance;

@Service
public class AvanceService implements Avance, Action {

	@Override
	public Position avancer(Position positionInitiale, double orientation, Perimetre perimetre) {

		DeplacementService deplacementService = new DeplacementService();

		PerimetreService perimetreService = new PerimetreService();

		Position positionFinale = Position.builder()
				.x(deplacementService.seDeplacerSelonX(positionInitiale.getX(), orientation))
				.y(deplacementService.seDeplacerSelonY(positionInitiale.getY(), orientation)).build();

		if (perimetreService.inPerimetre(positionFinale, perimetre))
			return positionFinale;
		else
			return positionInitiale;

	}

	@Override
	public Tondeuse run(Tondeuse tondeuse, Perimetre perimetre) {
		return Tondeuse.builder()
				.position(this.avancer(tondeuse.getPosition(), tondeuse.getOrientation(), perimetre))
				.orientation(tondeuse.getOrientation()).build();
	}
}
