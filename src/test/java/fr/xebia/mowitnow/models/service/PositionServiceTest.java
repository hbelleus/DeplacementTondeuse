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

import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.models.services.PositionService;
import fr.xebia.mowitnow.services.interfaces.RecuperationPosition;

@RunWith(MockitoJUnitRunner.class)
public class PositionServiceTest {

	@Mock
	private RecuperationPosition recuperationPositionService;

	@InjectMocks
	private PositionService positionService;

	@Test
	public void initTest() {

		List<String> lignePositionInitiale = Arrays.asList("1", "3");

		Mockito.when(recuperationPositionService.recupererPositionSelonXFrom(lignePositionInitiale))
				.thenReturn(1);
		Mockito.when(recuperationPositionService.recupererPositionSelonYFrom(lignePositionInitiale))
				.thenReturn(3);

		Position position = positionService.init(lignePositionInitiale);

		Assert.assertThat(position.getX(), Matchers.is(1));
		Assert.assertThat(position.getY(), Matchers.is(3));
	}
}
