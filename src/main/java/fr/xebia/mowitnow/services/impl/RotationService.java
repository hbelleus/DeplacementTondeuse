package fr.xebia.mowitnow.services.impl;

import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.services.interfaces.Rotation;

@Service
public class RotationService implements Rotation {

	public double tournerAGauche(double orientationIntiale) {
		return orientationIntiale + (Math.PI / 2);
	}

	public double tournerADroite(double orientationIntiale) {
		return orientationIntiale - (Math.PI / 2);
	}
}
