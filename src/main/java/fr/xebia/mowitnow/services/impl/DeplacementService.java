package fr.xebia.mowitnow.services.impl;

import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.services.interfaces.Deplacement;

@Service
public class DeplacementService implements Deplacement {

	@Override
	public Integer seDeplacerSelonX(Integer x, double orientation) {
		return x + (int) Math.cos(orientation);
	}

	@Override
	public Integer seDeplacerSelonY(Integer y, double orientation) {
		return y + (int) Math.sin(orientation);
	}

	@Override
	public Tondeuse seDeplacer(Tondeuse tondeuseAvantCmd, Tondeuse tondeuseApresCmd) {

		tondeuseAvantCmd.setPosition(tondeuseApresCmd.getPosition());
		tondeuseAvantCmd.setOrientation(tondeuseApresCmd.getOrientation());

		return tondeuseAvantCmd;
	}
}
