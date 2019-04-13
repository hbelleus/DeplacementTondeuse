package fr.xebia.mowitnow.constantes;

public enum Commands {

	TURN_RIGHT("D"), TURN_LEFT("G"), MOVE_FORWARD("A");

	public String command;

	Commands(String command) {
		this.command = command;
	}
}
