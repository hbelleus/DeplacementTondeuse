package fr.xebia.mowitnow.services.impl;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import fr.xebia.mowitnow.models.Position;

public class RecuperationPositionServiceTest {

	@InjectMocks
	private RecuperationPositionService recuperationPositionService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void recupererPositionSelonXFromTest() {

		Position position = Position.builder()
				.x(recuperationPositionService.recupererPositionSelonXFrom(getLignePositionInitiale()))
				.build();

		Assert.assertThat(position.getX(), is(1));
		Assert.assertTrue(Objects.isNull(position.getY()));
	}

	@Test
	public void recupererPositionSelonYFromTest() {

		Position position = Position.builder()
				.y(recuperationPositionService.recupererPositionSelonYFrom(getLignePositionInitiale()))
				.build();

		Assert.assertThat(position.getY(), is(2));
		Assert.assertTrue(Objects.isNull(position.getX()));
	}

	private List<String> getLignePositionInitiale() {
		return Arrays.asList("1", "2", "N");
	}
}
