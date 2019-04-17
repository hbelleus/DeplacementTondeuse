package fr.xebia.mowitnow.services.impl;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.models.Tondeuse;

@RunWith(MockitoJUnitRunner.class)
public class DeplacementServiceTest {

	@InjectMocks
	private DeplacementService deplacementService;

	@Test
	public void seDeplacerSelonXTest() {

		Integer result = deplacementService.seDeplacerSelonX(1, 0);

		Assert.assertThat(result, Matchers.is(2));
	}

	@Test
	public void seDeplacerSelonYTest() {

		Integer result = deplacementService.seDeplacerSelonY(1, 0);

		Assert.assertThat(result, Matchers.is(1));
	}

	@Test
	public void seDeplacerTest() {

		Tondeuse tondeuseAvantCmd = Tondeuse.builder().position(Position.builder().x(2).y(0).build())
				.orientation(Math.PI).build();
		Tondeuse tondeuseApresCmd = Tondeuse.builder().position(Position.builder().x(1).y(0).build())
				.orientation(Math.PI).build();

		Tondeuse result = deplacementService.seDeplacer(tondeuseAvantCmd, tondeuseApresCmd);

		Assert.assertThat(result.getPosition().getX(), Matchers.is(1));
		Assert.assertThat(result.getPosition().getY(), Matchers.is(0));
		Assert.assertThat(result.getOrientation(), Matchers.is(Math.PI));
	}
}
