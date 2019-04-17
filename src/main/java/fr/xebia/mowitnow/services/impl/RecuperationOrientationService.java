package fr.xebia.mowitnow.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.constantes.Direction;
import fr.xebia.mowitnow.services.interfaces.RecuperationOrientation;

@Service
public class RecuperationOrientationService implements RecuperationOrientation, InitializingBean {

	private Map<String, Double>	directions		= new HashMap<>(4);
	private Map<Double, String>	orientations	= new HashMap<>(4);

	@Override
	public void afterPropertiesSet() throws Exception {

		directions.put(Direction.N.name(), Direction.N.angle);
		directions.put(Direction.E.name(), Direction.E.angle);
		directions.put(Direction.S.name(), Direction.S.angle);
		directions.put(Direction.W.name(), Direction.W.angle);

		orientations.put(Direction.N.angle, Direction.N.name());
		orientations.put(Direction.E.angle, Direction.E.name());
		orientations.put(Direction.S.angle, Direction.S.name());
		orientations.put(Direction.W.angle, Direction.W.name());

	}

	@Override
	public double recupererOrientationFrom(List<String> lignePositionInitiale) {
		return directions.get(lignePositionInitiale.get(2));
	}

	@Override
	public String getDirectionFrom(double orientation) {
		return orientations.keySet().stream().filter(angleSelectionnee(orientation))
				.map(orientations::get).findFirst().orElse("non trouvé");
	}

	private Predicate<Double> angleSelectionnee(double orientation) {
		return angle -> ((angle - orientation) % (2 * Math.PI)) == 0;
	}

}
