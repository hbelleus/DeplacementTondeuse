package fr.xebia.mowitnow.constantes;

public enum Direction {

	E(Math.PI * 2), N(Math.PI / 2), W(Math.PI), S(3 * Math.PI / 2);

	public double angle;

	Direction(double angle) {
		this.angle = angle;
	}

	public double angle() {
		return this.angle;
	}

}
