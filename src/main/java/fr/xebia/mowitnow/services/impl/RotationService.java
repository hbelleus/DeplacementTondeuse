package fr.xebia.mowitnow.services.impl;

import org.springframework.stereotype.Service;

@Service
public class RotationService {

	public double tournerAGauche(double orientationIntiale) {
		return orientationIntiale + (Math.PI / 2);
	}

	public double tournerADroite(double orientationIntiale) {
		return orientationIntiale - (Math.PI / 2);
	}
}
