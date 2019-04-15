package fr.xebia.mowitnow.services.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.xebia.mowitnow.constantes.Direction;
import fr.xebia.mowitnow.models.Perimetre;
import fr.xebia.mowitnow.models.Position;
import fr.xebia.mowitnow.models.Tondeuse;
import fr.xebia.mowitnow.models.services.PerimetreService;
import fr.xebia.mowitnow.models.services.TondeuseService;
import fr.xebia.mowitnow.reader.interfaces.Reader;

public class ProcessorServiceTest {

	@Mock
	private Reader fileTxtReader;

	@Mock
	private TondeuseService tondeuseService;

	@Mock
	private PerimetreService perimetreService;

	@InjectMocks
	private ProcessorService processorService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		Mockito.when(fileTxtReader.read()).thenReturn(getLignesEntreeMock());

		Mockito.when(perimetreService.initialiserPerimetre(getLignesEntreeMock().get(0)))
				.thenReturn(getPerimetreMock());

		Mockito.when(tondeuseService.determinerPositionsFinale(Mockito.anyList(),
				Mockito.isA(Perimetre.class))).thenReturn(getTondeuseMock());
	}

	@Test
	public void processTest() {

		List<Tondeuse> tondeuses = processorService.process();

		Assert.assertEquals(1, tondeuses.size());
		Assert.assertEquals(Direction.N.name(), tondeuses.get(0).getDirectionFinale());
	}

	private List<List<String>> getLignesEntreeMock() {

		List<String> lignePerimetre = Arrays.asList("1", "6");

		List<String> lignePositionTondeuse = Arrays.asList("2", "3", "N");

		List<String> ligneCommandesTondeuse = Arrays.asList("G", "A", "D", "A");

		return Arrays.asList(lignePerimetre, lignePositionTondeuse, ligneCommandesTondeuse);
	}

	private Perimetre getPerimetreMock() {
		return Perimetre.builder().limiteSelonX(1).limiteSelonY(6).build();
	}

	private Tondeuse getTondeuseMock() {
		return Tondeuse.builder().position(Position.builder().x(2).y(3).build())
				.orientation(Math.PI / 2).directionFinale("N").build();
	}
}
