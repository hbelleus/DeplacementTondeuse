package fr.xebia.mowitnow.models.service;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.models.services.PerimetreService;

@RunWith(MockitoJUnitRunner.class)
public class PerimetreServiceTest {

	@InjectMocks
	private PerimetreService perimetreService;

	@Test
	public void initialiserPerimetreTest() {

		Perimetre perimetre = perimetreService.initialiserPerimetre(Arrays.asList("1", "2"));

		Assert.assertEquals(getPerimetreMock().getLimiteSelonX(), perimetre.getLimiteSelonX());
		Assert.assertEquals(getPerimetreMock().getLimiteSelonY(), perimetre.getLimiteSelonY());
	}

	@Test
	public void inPerimetreTest_XOutOfPerimetreInf() {

		boolean inPerimetre = perimetreService.inPerimetre(getPositionOutOfPerimetreInfSelonXMock(),
				getPerimetreMock());

		Assert.assertFalse(inPerimetre);
	}

	@Test
	public void inPerimetreTest_YOutOfPerimetreInf() {

		boolean inPerimetre = perimetreService.inPerimetre(getPositionOutOfPerimetreInfSelonYMock(),
				getPerimetreMock());

		Assert.assertFalse(inPerimetre);
	}

	@Test
	public void inPerimetreTest_XAndYOutOfPerimetreInf() {

		boolean inPerimetre = perimetreService.inPerimetre(getPositionOutOfPerimetreInfMock(),
				getPerimetreMock());

		Assert.assertFalse(inPerimetre);
	}

	@Test
	public void inPerimetreTest_XOutOfPerimetreSup() {

		boolean inPerimetre = perimetreService.inPerimetre(getPositionOutOfPerimetreSupSelonXMock(),
				getPerimetreMock());

		Assert.assertFalse(inPerimetre);
	}

	@Test
	public void inPerimetreTest_YOutOfPerimetreSup() {

		boolean inPerimetre = perimetreService.inPerimetre(getPositionOutOfPerimetreSupSelonYMock(),
				getPerimetreMock());

		Assert.assertFalse(inPerimetre);
	}

	@Test
	public void inPerimetreTest_XInPerimetre() {

		boolean inPerimetre = perimetreService.respecteLimiteSelonX(
				getPositionInPerimetreMock().getX(), getPerimetreMock().getLimiteSelonX());

		Assert.assertTrue(inPerimetre);
	}

	@Test
	public void inPerimetreTest_YInPerimetre() {

		boolean inPerimetre = perimetreService.respecteLimiteSelonY(
				getPositionInPerimetreMock().getY(), getPerimetreMock().getLimiteSelonY());

		Assert.assertTrue(inPerimetre);
	}

	@Test
	public void respecteLimiteSelonX_XInPerimetre() {

		boolean inPerimetre = perimetreService.respecteLimiteSelonX(
				-1, getPerimetreMock().getLimiteSelonX());

		Assert.assertFalse(inPerimetre);
	}

	@Test
	public void respecteLimiteSelonY_YInPerimetre() {

		boolean inPerimetre = perimetreService.respecteLimiteSelonY(
				-1, getPerimetreMock().getLimiteSelonY());

		Assert.assertFalse(inPerimetre);
	}

	private Perimetre getPerimetreMock() {
		return Perimetre.builder().limiteSelonX(1).limiteSelonY(2).build();
	}

	private Position getPositionOutOfPerimetreSupSelonXMock() {
		return Position.builder().x(2).y(1).build();
	}

	private Position getPositionOutOfPerimetreSupSelonYMock() {
		return Position.builder().x(1).y(3).build();
	}

	private Position getPositionOutOfPerimetreInfSelonXMock() {
		return Position.builder().x(-1).y(0).build();
	}

	private Position getPositionOutOfPerimetreInfSelonYMock() {
		return Position.builder().x(0).y(-1).build();
	}

	private Position getPositionOutOfPerimetreInfMock() {
		return Position.builder().x(-1).y(-1).build();
	}

	private Position getPositionInPerimetreMock() {
		return Position.builder().x(0).y(0).build();
	}
}
