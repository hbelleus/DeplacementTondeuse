package fr.xebia.mowitnow.reader.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class FileTxtReaderTest {

	@InjectMocks
	private FileTxtReader reader;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void readTest() {

		ReflectionTestUtils.setField(reader, "path", "D:/Profiles/hbelleus/Desktop/commandes.txt");

		List<List<String>> listFromFile = reader.read();

		Assert.assertEquals(5, listFromFile.size());

	}
}
