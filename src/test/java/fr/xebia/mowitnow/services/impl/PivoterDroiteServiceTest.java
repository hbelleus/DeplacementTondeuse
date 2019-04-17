package fr.xebia.mowitnow.services.impl;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.services.interfaces.Rotation;

@RunWith(MockitoJUnitRunner.class)
public class PivoterDroiteServiceTest {

	@Mock
	private Rotation rotationService;

	@InjectMocks
	private PivoterDroiteService pivoterDroiteService;

	@Test
	public void runTest() {

		Mockito.when(rotationService.tournerADroite(Mockito.isA(Double.class)))
				.thenReturn(Math.PI / 2);

		Tondeuse result = pivoterDroiteService.run(getTondeuseMock(), getPerimetreMock());

		Mockito.verify(rotationService, Mockito.times(1)).tournerADroite(Mockito.isA(Double.class));

		Assert.assertThat(result.getPosition().getX(), Matchers.is(2));
		Assert.assertThat(result.getPosition().getY(), Matchers.is(3));
		Assert.assertThat(result.getOrientation(), Matchers.is(Math.PI / 2));
	}

	private Perimetre getPerimetreMock() {
		return Perimetre.builder().limiteSelonX(4).limiteSelonY(4).build();
	}

	private Position getPositionMock() {
		return Position.builder().x(2).y(3).build();
	}

	private Tondeuse getTondeuseMock() {
		return Tondeuse.builder().position(getPositionMock()).orientation(Math.PI).build();
	}
}
