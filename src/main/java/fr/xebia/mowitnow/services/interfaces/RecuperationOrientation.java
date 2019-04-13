package fr.xebia.mowitnow.services.interfaces;

import java.util.List;

public interface RecuperationOrientation {

	double recupererOrientationFrom(List<String> lignePositionInitiale);

	String getDirectionFrom(double orientation);
}
