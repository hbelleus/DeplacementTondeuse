package fr.xebia.mowitnow.services.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import fr.xebia.mowitnow.constantes.Direction;

public class RotationServiceTest {

	@InjectMocks
	private RotationService rotationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void tournerADroiteTest_FromNorth() {

		double angle = rotationService.tournerADroite(Direction.N.angle);

		Assert.assertEquals(Math.cos(Direction.E.angle), Math.cos(angle), 1E-10);
		Assert.assertEquals(Math.sin(Direction.E.angle), Math.sin(angle), 1E-10);

	}

	@Test
	public void tournerADroiteTest_FromSouth() {

		double angle = rotationService.tournerADroite(Direction.S.angle);

		Assert.assertEquals(Math.cos(Direction.W.angle), Math.cos(angle), 1E-10);
		Assert.assertEquals(Math.sin(Direction.W.angle), Math.sin(angle), 1E-10);

	}

	@Test
	public void tournerAGaucheTest_FromEast() {

		double angle = rotationService.tournerAGauche(Direction.E.angle);

		Assert.assertEquals(Math.cos(Direction.N.angle), Math.cos(angle), 1E-10);
		Assert.assertEquals(Math.sin(Direction.N.angle), Math.sin(angle), 1E-10);

	}

	@Test
	public void tournerAGaucheTest_FromWest() {

		double angle = rotationService.tournerAGauche(Direction.W.angle);

		Assert.assertEquals(Math.cos(Direction.S.angle), Math.cos(angle), 1E-10);
		Assert.assertEquals(Math.sin(Direction.S.angle), Math.sin(angle), 1E-10);

	}
}
