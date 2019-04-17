package fr.xebia.mowitnow.models.service;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import fr.xebia.mowitnow.constantes.Direction;
import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.models.services.PositionService;
import fr.xebia.mowitnow.models.services.TondeuseService;
import fr.xebia.mowitnow.services.interfaces.Commande;
import fr.xebia.mowitnow.services.interfaces.Deplacement;
import fr.xebia.mowitnow.services.interfaces.RecuperationOrientation;

@RunWith(MockitoJUnitRunner.class)
public class TondeuseServiceTest {

	@Mock
	private Commande commandeService;

	@Mock
	private Deplacement deplacementService;

	@Mock
	private RecuperationOrientation recuperationOrientationService;

	@Mock
	private PositionService positionService;

	@InjectMocks
	private TondeuseService tondeuseService;

	@Test
	public void determinerPositionsFinaleTest() {

		Mockito.when(positionService.init(getLignesActionTondeuseMock().get(0)))
				.thenReturn(getPositionMock());
		Mockito
				.when(recuperationOrientationService
						.recupererOrientationFrom(getLignesActionTondeuseMock().get(0)))
				.thenReturn(Direction.W.angle);

		Mockito.when(commandeService.executer(Mockito.isA(String.class), Mockito.isA(Tondeuse.class),
				Mockito.isA(Perimetre.class))).thenReturn(
						Tondeuse.builder().position(getPositionMock()).orientation(Math.PI).build());

		Mockito.when(recuperationOrientationService.getDirectionFrom(Mockito.anyDouble()))
				.thenReturn(Direction.N.name());

		Tondeuse tondeuse = tondeuseService.determinerPositionsFinale(getLignesActionTondeuseMock(),
				getPerimetreMock());

		Mockito.verify(commandeService, Mockito.times(4)).executer(Mockito.isA(String.class),
				Mockito.isA(Tondeuse.class), Mockito.isA(Perimetre.class));

		Mockito.verify(deplacementService, Mockito.times(4)).seDeplacer(Mockito.isA(Tondeuse.class),
				Mockito.isA(Tondeuse.class));

		Assert.assertThat(tondeuse.getDirectionFinale(), Matchers.is("N"));
	}

	private Perimetre getPerimetreMock() {
		return Perimetre.builder().limiteSelonX(4).limiteSelonY(4).build();
	}

	private Position getPositionMock() {
		return Position.builder().x(2).y(3).build();
	}

	private List<List<String>> getLignesActionTondeuseMock() {

		List<String> lignePositionTondeuse = Arrays.asList("2", "3", "W");

		List<String> ligneCommandesTondeuse = Arrays.asList("G", "A", "D", "A");

		return Arrays.asList(lignePositionTondeuse, ligneCommandesTondeuse);
	}
}
