package fr.xebia.mowitnow.models.services;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.util.LineUtils;

@Component
public class PerimetreService {

	public Perimetre initialiserPerimetre(List<String> lignePerimetre) {

		int x = Integer.valueOf(LineUtils.premierElementOf(lignePerimetre));

		int y = Integer.valueOf(LineUtils.secondElementOf(lignePerimetre));

		return this.determinerPerimetre(x, y);
	}

	public Perimetre determinerPerimetre(int x, int y) {
		return Perimetre.builder().limiteSelonX(x).limiteSelonY(y).build();
	}

	public boolean inPerimetre(Position positionApresCommande, Perimetre perimetre) {
		return respecteLimiteSelonX(positionApresCommande.getX(), perimetre.getLimiteSelonX())
				&& respecteLimiteSelonY(positionApresCommande.getY(), perimetre.getLimiteSelonY());
	}

	public boolean respecteLimiteSelonX(int xApresCommande, int limiteSelonX) {
		return 0 <= xApresCommande && limiteSelonX + 1 >= xApresCommande;

	}

	public boolean respecteLimiteSelonY(int yApresCommande, int limiteSelonY) {
		return 0 <= yApresCommande && limiteSelonY + 1 >= yApresCommande;
	}
}
