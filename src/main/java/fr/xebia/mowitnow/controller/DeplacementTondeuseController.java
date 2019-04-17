package fr.xebia.mowitnow.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.xebia.mowitnow.services.interfaces.Processor;

@RestController
public class DeplacementTondeuseController {

	@Autowired
	private Processor processorService;

	@RequestMapping(value = "getPositionFinale", method = RequestMethod.GET)
	public List<String> getPositionFinale() {
		return processorService.process().stream().map(Object::toString).collect(Collectors.toList());
	}
}
