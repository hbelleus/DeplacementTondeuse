package fr.xebia.mowitnow.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.services.interfaces.Action;

@RunWith(MockitoJUnitRunner.class)
public class CommandeServiceTest {

	@Mock
	private Action pivoterDroiteService;

	@Mock
	private Action pivoterGaucheService;

	@Mock
	private Action avanceService;

	@InjectMocks
	private CommandeService commandeService;

	@Before
	public void setUp() throws Exception {
		commandeService.afterPropertiesSet();
	}

	@Test
	public void executerTest() {

		commandeService.executer("A", getTondeuseMock(), getPerimetreMock());

		Mockito.verify(avanceService, Mockito.times(1)).run(Mockito.isA(Tondeuse.class),
				Mockito.isA(Perimetre.class));
	}

	private Perimetre getPerimetreMock() {
		return Perimetre.builder().limiteSelonX(4).limiteSelonY(4).build();
	}

	private Position getPositionMock() {
		return Position.builder().x(1).y(2).build();
	}

	private Tondeuse getTondeuseMock() {
		return Tondeuse.builder().position(getPositionMock()).orientation(Math.PI).build();
	}
}
