package fr.xebia.mowitnow.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Tondeuse {

	private Position position;

	private double orientation;

	private String directionFinale;

	@Override
	public String toString() {
		return position.getX().toString() + " " + position.getY().toString() + " " + directionFinale;
	}

}
