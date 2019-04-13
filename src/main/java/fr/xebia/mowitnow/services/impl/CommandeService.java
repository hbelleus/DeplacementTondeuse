package fr.xebia.mowitnow.services.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.xebia.mowitnow.constantes.Commands;
import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.services.interfaces.Action;
import fr.xebia.mowitnow.services.interfaces.Commande;

@Service
public class CommandeService implements Commande, InitializingBean {

	@Autowired
	private Action pivoterDroiteService;

	@Autowired
	private Action pivoterGaucheService;

	@Autowired
	private Action avanceService;

	private Map<String, Action> commandesToActions = new ConcurrentHashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {

		commandesToActions.put(Commands.TURN_RIGHT.command, pivoterDroiteService);
		commandesToActions.put(Commands.TURN_LEFT.command, pivoterGaucheService);
		commandesToActions.put(Commands.MOVE_FORWARD.command, avanceService);

	}

	@Override
	public Tondeuse executer(String cmd, Tondeuse tondeuse, Perimetre perimetre) {
		return commandesToActions.get(cmd).run(tondeuse, perimetre);
	}

}
