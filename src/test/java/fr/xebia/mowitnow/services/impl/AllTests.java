package fr.xebia.mowitnow.services.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AvanceServiceTest.class, CommandeServiceTest.class, DeplacementServiceTest.class,
		PivoterDroiteServiceTest.class, PivoterGaucheServiceTest.class, ProcessorServiceTest.class,
		RecuperationOrientationServiceTest.class, RecuperationPositionServiceTest.class,
		RotationServiceTest.class })
public class AllTests {

}
