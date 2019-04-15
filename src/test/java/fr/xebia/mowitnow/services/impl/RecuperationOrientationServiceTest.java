package fr.xebia.mowitnow.services.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import fr.xebia.mowitnow.constantes.Direction;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RecuperationOrientationServiceTest {

	@InjectMocks
	private RecuperationOrientationService recuperationOrientationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		try {
			recuperationOrientationService.afterPropertiesSet();
		} catch (Exception e) {
			log.warn("Impossible de lancer la méthode d'initialisation", e);
		}
	}

	@Test
	public void recupererOrientationFromTest() {

		double orientation = recuperationOrientationService
				.recupererOrientationFrom(getLignePositionInitiale());

		Assert.assertEquals(Direction.N.angle, orientation, 0);
	}

	@Test
	public void getDirectionFromTest() {

		String direction = recuperationOrientationService.getDirectionFrom(Direction.S.angle);

		Assert.assertEquals(Direction.S.name(), direction);
	}

	@Test
	public void getDirectionFromTest_orElse() {

		String direction = recuperationOrientationService.getDirectionFrom(1);

		Assert.assertTrue(direction.contains("non"));
	}

	private List<String> getLignePositionInitiale() {
		return Arrays.asList("1", "2", "N");
	}
}
