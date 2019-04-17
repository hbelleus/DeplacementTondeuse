package fr.xebia.mowitnow.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tondeuse {

	private Position position;

	private double orientation;

	private String directionFinale;

	@Override
	public String toString() {
		return position.getX().toString() + " " + position.getY().toString() + " " + directionFinale;
	}

}
