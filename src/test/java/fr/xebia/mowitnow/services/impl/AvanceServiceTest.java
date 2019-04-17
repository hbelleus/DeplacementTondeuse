package fr.xebia.mowitnow.services.impl;

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
import fr.xebia.mowitnow.models.services.PerimetreService;
import fr.xebia.mowitnow.services.interfaces.Deplacement;

@RunWith(MockitoJUnitRunner.class)
public class AvanceServiceTest {

	@Mock
	private Deplacement deplacementService;

	@Mock
	private PerimetreService perimetreService;

	@InjectMocks
	private AvanceService avanceService;

	@Test
	public void runTestInPerimeter() {

		Mockito.when(deplacementService.seDeplacerSelonX(Mockito.isA(Integer.class),
				Mockito.isA(Double.class))).thenReturn(3);
		Mockito.when(deplacementService.seDeplacerSelonY(Mockito.isA(Integer.class),
				Mockito.isA(Double.class))).thenReturn(4);

		Mockito.when(
				perimetreService.inPerimetre(Mockito.isA(Position.class), Mockito.isA(Perimetre.class)))
				.thenReturn(true);

		Tondeuse tondeuse = avanceService.run(getTondeuseMock(), getPerimetreMock());

		Assert.assertThat(tondeuse.getPosition().getX(), Matchers.is(3));
		Assert.assertThat(tondeuse.getPosition().getY(), Matchers.is(4));
		Assert.assertThat(tondeuse.getOrientation(), Matchers.is(Direction.W.angle()));
	}

	@Test
	public void runTestOutPerimeter() {

		Mockito.when(deplacementService.seDeplacerSelonX(Mockito.isA(Integer.class),
				Mockito.isA(Double.class))).thenReturn(3);
		Mockito.when(deplacementService.seDeplacerSelonY(Mockito.isA(Integer.class),
				Mockito.isA(Double.class))).thenReturn(4);

		Mockito.when(
				perimetreService.inPerimetre(Mockito.isA(Position.class), Mockito.isA(Perimetre.class)))
				.thenReturn(false);

		Tondeuse tondeuse = avanceService.run(getTondeuseMock(), getPerimetreMock());

		Assert.assertThat(tondeuse.getPosition().getX(), Matchers.is(1));
		Assert.assertThat(tondeuse.getPosition().getY(), Matchers.is(2));
		Assert.assertThat(tondeuse.getOrientation(), Matchers.is(Direction.W.angle()));
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
