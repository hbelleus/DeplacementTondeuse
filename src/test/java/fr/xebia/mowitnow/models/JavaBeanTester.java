package fr.xebia.mowitnow.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.mockito.Mockito;

import lombok.extern.slf4j.Slf4j;

/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Rob Dawson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This helper class can be used to unit test the get/set methods of
 * JavaBean-style Value Objects.
 *
 * @author rob.dawson
 *
 **/
@Slf4j
public class JavaBeanTester {

	private JavaBeanTester() {
	}

	/**
	 * Tests the get/set methods of the specified class.
	 *
	 * @param <T>
	 *           the type parameter associated with the class under test
	 * @param clazz
	 *           the Class under test
	 * @param skipThese
	 *           the names of any properties that should not be tested
	 * @throws IntrospectionException
	 *            thrown if the Introspector.getBeanInfo() method throws this
	 *            exception for the class under test
	 */
	public static <T> void test(final Class<T> clazz, final String... skipThese)
			throws IntrospectionException {
		final PropertyDescriptor[] props = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
		for (PropertyDescriptor prop : props) {

			final Method getter = prop.getReadMethod();
			final Method setter = prop.getWriteMethod();

			if (getter != null && setter != null) {
				// We have both a get and set method for this property
				final Class<?> returnType = getter.getReturnType();
				final Class<?>[] params = setter.getParameterTypes();

				if (params.length == 1 && params[0] == returnType) {
					// The set method has 1 argument, which is of the same type as the return type
					// of the get method, so we can test this property
					try {
						// Build a value of the correct type to be passed to the set method
						Object value = buildValue(returnType);

						// Build an instance of the bean that we are testing (each property test gets a
						// new instance)
						T bean = clazz.newInstance();

						// Call the set method, then check the same value comes back out of the get
						// method
						setter.invoke(bean, value);

						final Object expectedValue = value;
						final Object actualValue = getter.invoke(bean);

						assertEquals(String.format("Failed while testing property %s", prop.getName()),
								expectedValue, actualValue);

					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| SecurityException | InvocationTargetException ex) {
						log.error("Erreur: {}", ex);
						fail(String.format("An exception was thrown while testing the property %s: %s",
								prop.getName(), ex.toString()));
					}
				}
			}
		}
	}

	private static Object buildMockValue(Class<?> clazz) {
		if (!Modifier.isFinal(clazz.getModifiers()))
			// Insert a call to your favourite mocking framework here
			return Mockito.mock(clazz);
		else
			return null;
	}

	private static Object buildValue(Class<?> clazz)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			SecurityException, InvocationTargetException {
		// If we are using a Mocking framework try that first...
		final Object mockedObject = buildMockValue(clazz);
		if (mockedObject != null)
			return mockedObject;

		// Next check for a no-arg constructor
		final Constructor<?>[] ctrs = clazz.getConstructors();
		for (Constructor<?> ctr : ctrs) {
			if (ctr.getParameterTypes().length == 0)
				// The class has a no-arg constructor, so just call it
				return ctr.newInstance();
		}

		return createCommonInstance(clazz);
	}

	private static Object createCommonInstance(Class<?> clazz) {
		// Specific rules for common classes
		if (clazz == int.class || clazz == Integer.class)
			return 1;
		else if (clazz == double.class)
			return 1.0d;
		else {
			fail("Unable to build an instance of class " + clazz.getName()
					+ ", please add some code to the " + JavaBeanTester.class.getName()
					+ " class to do this.");
			return null; // for the compiler
		}
	}

}