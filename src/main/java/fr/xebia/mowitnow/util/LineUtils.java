package fr.xebia.mowitnow.util;

import java.util.List;

public class LineUtils {

	private LineUtils() {
	}

	public static List<String> premierLigneOf(List<List<String>> lignes) {
		return lignes.get(0);
	}

	public static List<String> secondeLigneOf(List<List<String>> lignes) {
		return lignes.get(1);
	}

	public static String premierElementOf(List<String> ligne) {
		return ligne.get(0);
	}

	public static String secondElementOf(List<String> ligne) {
		return ligne.get(1);
	}

}
