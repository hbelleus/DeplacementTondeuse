package fr.xebia.mowitnow.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.xebia.mowitnow.services.interfaces.Processor;

@Controller
public class DeplacementTondeuseController extends MowItNowController {

	@Autowired
	private Processor processorService;

	/**
	 * Point d'entrée de l'application. Cet méthode retourne les positions finales
	 * des tondeuses fournies dans le fichier
	 *
	 * @return
	 */
	@RequestMapping(value = "getFinalPosition", method = RequestMethod.GET)
	public List<String> getFinalPosition() {
		return processorService.process().stream().map(Object::toString).collect(Collectors.toList());
	}

	@RequestMapping(value = "finalPosition")
	public String getFinalPosition2(ModelMap modelMap) {

		modelMap.addAttribute("positions",
				processorService.process().stream().map(Object::toString).collect(Collectors.toList()));

		return "mowitnow/finalPosition";
	}
}
