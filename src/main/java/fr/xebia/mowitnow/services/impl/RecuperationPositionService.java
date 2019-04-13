package fr.xebia.mowitnow.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.services.interfaces.RecuperationPosition;

@Service
public class RecuperationPositionService implements RecuperationPosition {

	@Override
	public int recupererPositionSelonXDepuis(List<String> lignePositionInitiale) {
		return Integer.valueOf(lignePositionInitiale.get(0));
	}

	@Override
	public int recupererPositionSelonYDepuis(List<String> lignePositionInitiale) {
		return Integer.valueOf(lignePositionInitiale.get(1));
	}

}
