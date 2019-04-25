package fr.xebia.mowitnow.controller;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.file.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import fr.xebia.mowitnow.constantes.Direction;
import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.services.impl.ProcessorService;

@RunWith(MockitoJUnitRunner.class)
public class DeplacementTondeuseControllerTest {

	@Mock
	private ProcessorService processorService;

	@InjectMocks
	private DeplacementTondeuseController deplacementTondeuseController;

	@Test
	public void getPositionFinaleTest() {

		Mockito.when(processorService.process()).thenReturn(Arrays.asList(getTondeuse()));

		List<String> tondeuses = deplacementTondeuseController.getFinalPosition();

		Assert.assertThat(tondeuses.size(), is(1));
		Assert.assertThat(true, is(Matcher.match("5 10 S", tondeuses.get(0).toString(), false)));
	}

	private Tondeuse getTondeuse() {
		return Tondeuse.builder().position(Position.builder().x(5).y(10).build())
				.directionFinale(Direction.S.name()).build();
	}
}
