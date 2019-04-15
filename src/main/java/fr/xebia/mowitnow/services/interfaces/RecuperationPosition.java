package fr.xebia.mowitnow.services.interfaces;

import java.util.List;

public interface RecuperationPosition {

	int recupererPositionSelonXFrom(List<String> lignePositionInitiale);

	int recupererPositionSelonYFrom(List<String> lignePositionInitiale);
}
