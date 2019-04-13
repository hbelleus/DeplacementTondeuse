package fr.xebia.mowitnow.services.impl;

import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.services.interfaces.Action;

@Service
public class PivoterGaucheService implements Action {

	@Override
	public Tondeuse run(Tondeuse tondeuse, Perimetre perimetre) {
		return Tondeuse.builder().position(tondeuse.getPosition())
				.orientation(this.pivoterAGauche(tondeuse.getOrientation())).build();
	}

	public double pivoterAGauche(double orientation) {
		return new RotationService().tournerAGauche(orientation);
	}
}
