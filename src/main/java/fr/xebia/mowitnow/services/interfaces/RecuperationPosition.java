package fr.xebia.mowitnow.services.interfaces;

import java.util.List;

public interface RecuperationPosition {

	int recupererPositionSelonXDepuis(List<String> lignePositionInitiale);

	int recupererPositionSelonYDepuis(List<String> lignePositionInitiale);
}
