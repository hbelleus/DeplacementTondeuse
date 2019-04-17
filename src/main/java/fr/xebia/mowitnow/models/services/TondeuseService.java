package fr.xebia.mowitnow.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.services.interfaces.Commande;
import fr.xebia.mowitnow.services.interfaces.Deplacement;
import fr.xebia.mowitnow.services.interfaces.RecuperationOrientation;
import fr.xebia.mowitnow.util.LineUtils;

@Component
public class TondeuseService {

	@Autowired
	private Commande commandeService;

	@Autowired
	private Deplacement deplacementService;

	@Autowired
	private RecuperationOrientation recuperationOrientationService;

	@Autowired
	private PositionService positionService;

	public Tondeuse determinerPositionsFinale(List<List<String>> lignesPourActionTondeuse,
			Perimetre perimetre) {

		return this.recupererEtDeplacerTondeuse(lignesPourActionTondeuse, perimetre);
	}

	protected Tondeuse recupererEtDeplacerTondeuse(List<List<String>> lignesPourActionTondeuse,
			Perimetre perimetre) {

		List<String> ligneForPositionInitialeTondeuse = LineUtils
				.premierLigneOf(lignesPourActionTondeuse);

		List<String> ligneForCommandesDeplacement = LineUtils
				.secondeLigneOf(lignesPourActionTondeuse);

		Tondeuse tondeuse = initialiserTondeuseFrom(ligneForPositionInitialeTondeuse);

		return deplacerTondeuse(tondeuse, ligneForCommandesDeplacement, perimetre);
	}

	protected Tondeuse initialiserTondeuseFrom(List<String> lignePositionInitialeTondeuse) {
		return this.init(lignePositionInitialeTondeuse);
	}

	protected Tondeuse init(List<String> lignePositionInitialeTondeuse) {

		return Tondeuse.builder().position(positionService.init(lignePositionInitialeTondeuse))
				.orientation(recuperationOrientationService
						.recupererOrientationFrom(lignePositionInitialeTondeuse))
				.build();
	}

	protected Tondeuse deplacerTondeuse(Tondeuse tondeuse, List<String> ligneCommandesDeplacement,
			Perimetre perimetre) {

		ligneCommandesDeplacement.stream()
				.map(cmd -> commandeService.executer(cmd, tondeuse, perimetre))
				.forEach(newPosition -> deplacementService.seDeplacer(tondeuse, newPosition));

		tondeuse.setDirectionFinale(
				recuperationOrientationService.getDirectionFrom(tondeuse.getOrientation()));

		return tondeuse;
	}
}
