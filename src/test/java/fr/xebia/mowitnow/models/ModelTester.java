package fr.xebia.mowitnow.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModelTester {

	private static final List<String> EXCLUDED_BEANS = new ArrayList<>();

	@Before
	public void setup() {
	}

	@Test
	public void testGenericBeans() throws Exception {
		// create scanner and disable default filters (that is the 'false' argument)
		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
				false);
		// add include filters which matches all the classes (or use your own)
		provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

		// get matching classes defined in the package
		final Set<BeanDefinition> classes = provider
				.findCandidateComponents("fr.xebia.mowitnow.models");

		// this is how you can load the class type from BeanDefinition instance
		for (BeanDefinition bean : classes) {
			if (!EXCLUDED_BEANS.contains(bean.getBeanClassName())) {
				log.info("Testing : " + bean.getBeanClassName());
				Class<?> clazz = Class.forName(bean.getBeanClassName());
				JavaBeanTester.test(clazz);
			}
		}
	}
}
