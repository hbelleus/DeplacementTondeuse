package fr.xebia.mowitnow.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.services.interfaces.Action;
import fr.xebia.mowitnow.services.interfaces.Rotation;

@Service
public class PivoterDroiteService implements Action {

	@Autowired
	private Rotation rotationService;

	@Override
	public Tondeuse run(Tondeuse tondeuse, Perimetre perimetre) {
		return Tondeuse.builder().position(tondeuse.getPosition())
				.orientation(this.pivoterADroite(tondeuse.getOrientation())).build();
	}

	private double pivoterADroite(double orientation) {
		return rotationService.tournerADroite(orientation);
	}
}
