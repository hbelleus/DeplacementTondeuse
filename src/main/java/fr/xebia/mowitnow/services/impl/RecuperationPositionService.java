package fr.xebia.mowitnow.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.services.interfaces.RecuperationPosition;

@Service
public class RecuperationPositionService implements RecuperationPosition {

	@Override
	public int recupererPositionSelonXFrom(List<String> lignePositionInitiale) {
		return Integer.valueOf(lignePositionInitiale.get(0));
	}

	@Override
	public int recupererPositionSelonYFrom(List<String> lignePositionInitiale) {
		return Integer.valueOf(lignePositionInitiale.get(1));
	}

}
