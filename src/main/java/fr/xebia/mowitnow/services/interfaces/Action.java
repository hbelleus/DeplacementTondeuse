package fr.xebia.mowitnow.services.interfaces;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Tondeuse;

public interface Action {

	Tondeuse run(Tondeuse tondeuse, Perimetre perimetre);
}
