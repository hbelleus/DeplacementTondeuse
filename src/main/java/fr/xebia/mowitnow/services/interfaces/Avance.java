package fr.xebia.mowitnow.services.interfaces;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Position;

public interface Avance {

	Position avancer(Position positionInitiale, double orientation, Perimetre perimetre);
}
