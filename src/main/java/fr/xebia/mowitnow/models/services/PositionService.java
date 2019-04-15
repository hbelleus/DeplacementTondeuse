package fr.xebia.mowitnow.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.services.interfaces.RecuperationPosition;

@Component
public class PositionService {

	@Autowired
	private RecuperationPosition recuperationPositionService;

	public Position init(List<String> lignePositionInitiale) {

		return Position.builder()
				.x(recuperationPositionService.recupererPositionSelonXFrom(lignePositionInitiale))
				.y(recuperationPositionService.recupererPositionSelonYFrom(lignePositionInitiale))
				.build();
	}
}
