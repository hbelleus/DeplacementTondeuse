package fr.xebia.mowitnow.reader.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.xebia.mowitnow.reader.interfaces.Reader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileTxtReader implements Reader {

	@Value("${reader.file.path}")
	private String path;

	@Override
	public List<List<String>> read() {

		log.debug("lecture du fichier " + path);

		String line = StringUtils.EMPTY;
		String toGetAllCaracters = StringUtils.EMPTY;

		List<List<String>> commands = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

			while (Objects.nonNull(line = reader.readLine())) {

				String[] values = line.split(toGetAllCaracters);

				commands.add(Arrays.asList(values).stream().filter(StringUtils::isNotBlank)
						.collect(Collectors.toList()));

			}

		} catch (IOException e) {
			log.warn("Problème lors de la lecture du fichier", e);
		}

		return commands;
	}

}
