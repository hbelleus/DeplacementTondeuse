package fr.xebia.mowitnow.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.models.services.PerimetreService;
import fr.xebia.mowitnow.models.services.TondeuseService;
import fr.xebia.mowitnow.reader.interfaces.Reader;
import fr.xebia.mowitnow.services.interfaces.Processor;
import fr.xebia.mowitnow.util.LineUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProcessorService implements Processor {

	@Autowired
	private Reader fileTxtReader;

	@Autowired
	private TondeuseService tondeuseService;

	@Autowired
	private PerimetreService perimetreService;

	@Override
	public List<Tondeuse> process() {

		log.debug("Debut de l'opération");

		List<List<String>> lignesEntree = fileTxtReader.read();

		log.debug("Lignes d'entree : {}", lignesEntree.toString());

		Perimetre perimetre = perimetreService
				.initialiserPerimetre(LineUtils.premierLigneOf(lignesEntree));

		int nbrTondeuses = (lignesEntree.size() - 1) / 2;

		List<Tondeuse> tondeuses = new ArrayList<>(nbrTondeuses);

		for (int i = 1; i < lignesEntree.size(); i += 2) {

			Tondeuse tondeuseInPositionFinale = null;

			try {
				tondeuseInPositionFinale = tondeuseService
						.recupererEtDeplacerTondeuse(lignesEntree.subList(i, (i + 2)), perimetre);

			} catch (IndexOutOfBoundsException e) {
				log.warn("Risque maitrisé - la valeur de i dépasse le nombre d'éléments de la liste",
						e);
			}

			log.info("Position finale de la tondeuse {}: {}", ((i + 1) / 2), tondeuseInPositionFinale);

			tondeuses.add(tondeuseInPositionFinale);
		}

		return tondeuses;
	}
}
